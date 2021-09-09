package com.example.projetMedical.repositories;

import com.example.projetMedical.model.entities.CitiesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<CitiesEntity, Integer> {
}
