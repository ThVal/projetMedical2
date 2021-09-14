package com.example.projetMedical.model.services;

import com.example.projetMedical.model.entities.PatientsEntity;
import com.example.projetMedical.model.repositories.PatientRepository;
import javassist.tools.rmi.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class PatientService {



    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientsEntity> getAllpatients() {

        return (List<PatientsEntity>) patientRepository.findAll();
    }

    public Optional<PatientsEntity> getPatientById(int id) {
        return patientRepository.findById(id);  // optional gere une éventualité
    }

    public PatientsEntity setAndSavePatient(PatientsEntity patient, String firstName, String lastname, String email, String phoneNumber, String picture) {

        patient.setFirstName(firstName);
        patient.setLastName(lastname);
        patient.setEmail(email);
        patient.setPhoneNumber(phoneNumber);
        patient.setEmail(picture);

        patientRepository.save(patient);
        return patient;

    }


    @Transactional
    // CREATE PATIENT
    public PatientsEntity addPatient(PatientsEntity patient, String firstName, String lastname, String email, String phoneNumber, String picture) {

        PatientsEntity user = new PatientsEntity();
        return setAndSavePatient(patient, firstName, lastname, email , phoneNumber, picture);

    }


    @Transactional
    public PatientsEntity updateUserById(int id, String firstName, String lastname, String email, String phoneNumber, String picture) {
        Optional<PatientsEntity> patientOptional = getPatientById(id);

        if (patientOptional.isPresent()) {
            return setAndSavePatient(patientOptional.get(), firstName, lastname, email , phoneNumber, picture);
        } else {
            throw new ObjectNotFoundException(id,"patient unknown");
        }

    }


    @Transactional
    public void deleteUserById(int id) {
        Optional<PatientsEntity> userOptional = getPatientById(id);

        if (userOptional.isPresent()) {
            patientRepository.delete(userOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "user unknown");

        }
    }







}
