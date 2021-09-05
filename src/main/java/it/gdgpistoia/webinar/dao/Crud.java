package it.gdgpistoia.webinar.dao;

import it.gdgpistoia.webinar.model.BaseEntity;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

public abstract class Crud<T extends BaseEntity>{

    @Inject
    protected EntityManager entityManager;

    public T create(T entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        entityManager.persist(entity);
        return entity;
    }

    public T read(Integer primaryKey) throws IllegalStateException, IllegalArgumentException{
        return entityManager.find(getGenericTypeClass(), primaryKey);
    }

    public T update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException{
        entity.setUpdated(new Date());
        return entityManager.merge(entity);
    }

    public T delete(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException{
        entityManager.remove(entity);
        return entity;
    }

    private Class<T> getGenericTypeClass() {
        try {
            Type genericSuperClass = getClass().getGenericSuperclass();
            ParameterizedType parametrizedType = null;
            while (parametrizedType == null) {
                if ((genericSuperClass instanceof ParameterizedType)) {
                    parametrizedType = (ParameterizedType) genericSuperClass;
                } else {
                    genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
                }
            }
            return (Class<T>) parametrizedType.getActualTypeArguments()[0];

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }

}
