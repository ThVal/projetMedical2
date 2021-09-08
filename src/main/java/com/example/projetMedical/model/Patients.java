package com.example.projetMedical.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="patients")

public class Patients {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Integer idPatient;


    @Basic
    private String lasName;

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
    private Cities city;


}
