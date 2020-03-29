package br.com.iser.liveloback.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    private String name;
    private Character gender;
    private LocalDate birthdate;
    private Integer age;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_City")
    private City city;
}
