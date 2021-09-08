package com.example.projetMedical.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="cities")
public class Cities {

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
        Cities cities = (Cities) o;
        return Objects.equals(idCity, cities.idCity) && Objects.equals(nom, cities.nom) && Objects.equals(zipCode, cities.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, nom, zipCode);
    }
}


