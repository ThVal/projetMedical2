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

    public CitiesEntity setAndSaveCity(CitiesEntity city,String name, String zipCode) {
        city.setNom(name);
        city.setZipCode(zipCode);


        cityRepository.save(city);
        return city;

    }


    @Transactional
    // CREATE USER
    public CitiesEntity addCity(String name, String zipCode) {

        CitiesEntity city = new CitiesEntity();
        return setAndSaveCity(city, name, zipCode);

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







