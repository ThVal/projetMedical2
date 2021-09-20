package com.example.projetMedical.model.services;

import com.example.projetMedical.model.entities.CitiesEntity;
import com.example.projetMedical.model.repositories.CityRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
//test
@Service

public class CityService {


    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository)
    {
        this.cityRepository = cityRepository;
    }

    public List<CitiesEntity> getAllCities() {

        return (List<CitiesEntity>) cityRepository.findAll();
    }

    public  Optional<CitiesEntity> getCityById(int id) {
        return cityRepository.findById(id);  // optional gere une éventualité
    }


    private CitiesEntity setCity(CitiesEntity city, String name, String zipCode) {
        city.setNom(name);
        city.setZipCode(zipCode);
        return city;


    }



    @Transactional
    // CREATE
    public CitiesEntity addCity(String name, String zipCode) {
        CitiesEntity city = new CitiesEntity();
        return cityRepository.save(setCity(city, name, zipCode));

    }


    @Transactional
    public CitiesEntity updateCityById(int id, String name, String zipCode) {
        Optional<CitiesEntity> cityOptional = getCityById(id);
        if (cityOptional.isPresent()) {
            return cityRepository.save(setCity(cityOptional.get(), name, zipCode));
        } else {
            throw new ObjectNotFoundException(id, "City not found");


        }
    }

    @Transactional
    public void deleteCityById(int id) {
        Optional<CitiesEntity> cityOptional = getCityById(id);

        if (cityOptional.isPresent()) {
            cityRepository.delete(cityOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "city unknown");

        }
    }

}







