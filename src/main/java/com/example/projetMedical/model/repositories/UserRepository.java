package com.example.projetMedical.model.repositories;

import com.example.projetMedical.model.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
}