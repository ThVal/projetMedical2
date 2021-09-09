package com.example.projetMedical.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(idUser, users.idUser) && Objects.equals(email, users.email) && Objects.equals(roles, users.roles) && Objects.equals(name, users.name) && Objects.equals(photoUser, users.photoUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, email, roles, name, photoUser);
    }
}
