package com.example.projetMedical.model.services;

import com.example.projetMedical.model.services.CityService;
import com.example.projetMedical.model.entities.CitiesEntity;
import com.example.projetMedical.model.entities.PatientsEntity;
import com.example.projetMedical.model.repositories.PatientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final CityService cityService;

    public PatientService(PatientRepository patientRepository, CityService cityService) {

        this.patientRepository = patientRepository;

        this.cityService = cityService;
    }

    public List<PatientsEntity> getAllPatients() {

        return (List<PatientsEntity>) patientRepository.findAll();
    }

    public Optional<PatientsEntity> getPatientById(int id) {

        return patientRepository.findById(id);  // optional gere une éventualité

    }

    public PatientsEntity setAndSavePatient(PatientsEntity patient, String firstName, String lastname, String email, String phoneNumber, String picture, CitiesEntity city) {

        patient.setFirstName(firstName);
        patient.setLastName(lastname);
        patient.setEmail(email);
        patient.setPhoneNumber(phoneNumber);
        patient.setEmail(picture);
        patient.setCity(city);

        patientRepository.save(patient);

        return patient;

    }

    @Transactional
// patient créé en dehors des param
    public PatientsEntity addPatient(String firstName, String lastname, String email, String phoneNumber, String picture, int idCity ) {

        PatientsEntity patient = new PatientsEntity();
        return setAndSavePatient(patient, firstName, lastname, email , phoneNumber, picture, cityService.getCityById(idCity).orElseThrow (
                () -> new ObjectNotFoundException(idCity, "city not found")
        ));

    }

    @Transactional
    public PatientsEntity updatePatientById(int id, String firstName, String lastname, String email, String phoneNumber, String picture, CitiesEntity city) {
        Optional<PatientsEntity> patientOptional = getPatientById(id);

        if (patientOptional.isPresent()) {
            return setAndSavePatient(patientOptional.get(), firstName, lastname, email , phoneNumber, picture, city);
        } else {
            throw new ObjectNotFoundException(id,"patient unknown");
        }

    }

    @Transactional
    public void deletePatientById(int id) {
        Optional<PatientsEntity> patientOptional = getPatientById(id);

        if (patientOptional.isPresent()) {
            patientRepository.delete(patientOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "user unknown");

        }
    }

}
