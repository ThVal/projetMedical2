package com.example.projetMedical.api;

import com.example.projetMedical.model.entities.PatientsEntity;
import com.example.projetMedical.model.services.PatientService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
//test
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("api/patients")
public class PatientControllerApi {

    private PatientService patientService;

    public PatientControllerApi(PatientService patientService) {

        this.patientService = patientService;
    }


    @GetMapping(path = "", produces = "application/json")
    public List<PatientsEntity> getPatientListApi() {
        return patientService.getAllPatients();
    }


    @GetMapping(path= "/{id}", produces = "application/json")
    public PatientsEntity getPatientById(@PathVariable("id") int id) {
        Optional<PatientsEntity> patientOptional = patientService.getPatientById(id);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user unknown");
        }

    }

    @PostMapping (path="", produces = "application/json")
    public ResponseEntity<PatientsEntity> addPatientApi(@RequestBody PatientsEntity patientInput) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    patientService.addPatient(
                            patientInput.getFirstName(),
                            patientInput.getLastName(),
                            patientInput.getEmail(),
                            patientInput.getPhoneNumber(),
                            patientInput.getPicture(),
                            patientInput.getCity().getIdCity()));

        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " City " + patientInput.getCity().getIdCity() + " not found");
        }

    }


    @PutMapping(path= "/{id}", produces = "application/json")
    public ResponseEntity<PatientsEntity> editPatientApi(@PathVariable("id") int id, @RequestBody PatientsEntity patientInput) {
            Optional<PatientsEntity> patientOptional = patientService.getPatientById(id);
            if (patientOptional.isPresent()) {
                try {
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                            patientService.updatePatientById(id,
                                    patientInput.getFirstName(),
                                    patientInput.getLastName(),
                                    patientInput.getEmail(),
                                    patientInput.getPhoneNumber(),
                                    patientInput.getPicture(),
                                    patientInput.getCity().getIdCity()));

                } catch (ObjectNotFoundException e) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, " City " + patientInput.getCity().getIdCity() + " not found");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient is not found");
            }
        }



    @DeleteMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<String> deletePatientApi(@PathVariable("id") int id) { // pourquoi String ?

            Optional<PatientsEntity> patientOptional = patientService.getPatientById(id);
            if (patientOptional.isPresent()) {
                patientService.deletePatientById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Patient " + id + " deleted");

            } else {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The patient is not found");
            }
        }

    }

