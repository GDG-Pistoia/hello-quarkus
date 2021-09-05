package it.gdgpistoia.webinar.model;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity{

    @Id
    @SequenceGenerator(name = "base")
    @GeneratedValue
    protected Integer id;
    protected String uuid;
    @JsonbDateFormat("dd-MM-yyyy HH:mm:ss")
    protected Date created;
    @JsonbDateFormat("dd-MM-yyyy HH:mm:ss")
    protected Date updated;

}
