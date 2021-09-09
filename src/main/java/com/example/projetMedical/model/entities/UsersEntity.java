package com.example.projetMedical.model.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name="users")

public class UsersEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement

    private Integer idUser;


    @Basic
    @NotNull
    private String email;

    @Column(nullable = false) // équivalent à mettre @notnull
    private String roles;

    @Column(nullable=false)
    private String Password;

    private String name;
    private String photoUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity usersEntity = (UsersEntity) o;
        return Objects.equals(idUser, usersEntity.idUser) && Objects.equals(email, usersEntity.email) && Objects.equals(roles, usersEntity.roles) && Objects.equals(name, usersEntity.name) && Objects.equals(photoUser, usersEntity.photoUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, email, roles, name, photoUser);
    }
}
