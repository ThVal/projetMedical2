package com.example.projetMedical.model.repositories;

import com.example.projetMedical.model.entities.PatientsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientsEntity, Integer> {
}