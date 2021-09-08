package com.example.projetMedical.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="users")

public class Users {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement

    private Integer idUser;


    @Basic
    @NotNull
    private String email;

    @Column(nullable = false) // équivalent à mettre @notnull
    private String roles;

    private String name;
    private String photoUser;


}
