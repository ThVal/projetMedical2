package com.example.projetMedical.api;



import com.example.projetMedical.model.entities.CitiesEntity;
import com.example.projetMedical.model.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")

public class CityControllerApi {
    private final CityService cityService;

    public CityControllerApi(CityService cityService) {

        this.cityService = cityService;
    }

    @GetMapping(path = "", produces = "application/json")
    public List<CitiesEntity> getCitiesListApi() {
        return cityService.getAllCities();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public CitiesEntity getCityByIdApi(@PathVariable("id") int id) {
        Optional<CitiesEntity> cityOptional = cityService.getCityById(id);
        if (cityOptional.isPresent()) {
            return cityOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

    /**
     * Add a new city
     */
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<CitiesEntity> addCityApi(@RequestBody CitiesEntity cityInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                cityService.addCity(
                        cityInput.getNom(),
                        cityInput.getZipCode()
                ));
    }

    /**
     * Edit a patient
     */
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<CitiesEntity> editCityApi(@PathVariable("id") int id, @RequestBody CitiesEntity cityInput) {
        Optional<CitiesEntity> cityOptional = cityService.getCityById(id);
        if (cityOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    cityService.updateCityById(id,
                            cityInput.getNom(),
                            cityInput.getZipCode()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteCityApi(@PathVariable("id") int id) {
        Optional<CitiesEntity> cityOptional = cityService.getCityById(id);
        if (cityOptional.isPresent()) {
            cityService.deleteCityById(id);
            return ResponseEntity.status(HttpStatus.OK).body("City " + id + " deleted");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The city is not found");
        }
    }

}
