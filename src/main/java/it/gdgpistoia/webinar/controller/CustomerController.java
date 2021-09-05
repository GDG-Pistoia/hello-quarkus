package it.gdgpistoia.webinar.controller;

import it.gdgpistoia.webinar.dao.CustomerDao;
import it.gdgpistoia.webinar.model.Customer;
import it.gdgpistoia.webinar.model.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.UUID;

@RequestScoped
public class CustomerController {

    @Inject
    CustomerDao customerDao;

    public Customer create(Customer customer){
        customer.setCreated(new Date());
        customer.setUpdated(new Date());
        customer.setUuid(UUID.randomUUID().toString());
        customerDao.create(customer);
        return customer;
    }

    public Customer read(Integer id){
        return customerDao.read(id);
    }

    public Product addProduct(Integer id, Product product) throws CustomerNotFoundException {
        Customer customer = customerDao.read(id);
        if(customer == null){
            throw new CustomerNotFoundException();
        }
        product.setCreated(new Date());
        product.setUpdated(new Date());
        product.setUuid(UUID.randomUUID().toString());
        customer.addProduct(product);
        return product;
    }
}
