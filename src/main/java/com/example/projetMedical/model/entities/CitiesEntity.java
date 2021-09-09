package com.example.projetMedical.model.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="cities")
public class CitiesEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer idCity;

    @Basic
    private String nom;
    @Basic
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CitiesEntity citiesEntity = (CitiesEntity) o;
        return Objects.equals(idCity, citiesEntity.idCity) && Objects.equals(nom, citiesEntity.nom) && Objects.equals(zipCode, citiesEntity.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, nom, zipCode);
    }
}


