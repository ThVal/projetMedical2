package com.example.projetMedical.model.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name="patients")

public class PatientsEntity {
    //test
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer idPatient;


    @Basic
    private String lastName;

    @Basic
    private String firstName;

    @Basic
    private String email;

    @Basic
    private String phoneNumber;

    @Basic
    private String picture;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name ="idCity")
    private CitiesEntity city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientsEntity patientsEntity = (PatientsEntity) o;
        return Objects.equals(idPatient, patientsEntity.idPatient) && Objects.equals(lastName, patientsEntity.lastName) && Objects.equals(firstName, patientsEntity.firstName) && Objects.equals(email, patientsEntity.email) && Objects.equals(phoneNumber, patientsEntity.phoneNumber) && Objects.equals(picture, patientsEntity.picture) && Objects.equals(city, patientsEntity.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPatient, lastName, firstName, email, phoneNumber, picture, city);
    }

}
