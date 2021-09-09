package com.example.projetMedical.model.services;


import com.example.projetMedical.model.entities.PatientsEntity;
import com.example.projetMedical.model.entities.UsersEntity;
import com.example.projetMedical.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersEntity> getAllusers() {

        return (List<UsersEntity>) userRepository.findAll();
    }

    public Optional<UsersEntity> getUserById(int id) {
        return userRepository.findById(id);  // optional gere une éventualité
    }


    @Transactional
    public UsersEntity addUser(String name, String email, String password, String roles, String photoUser) {

        UsersEntity user = new UsersEntity();
        user.setName(name);
        user.setPassword(password);
        user.setPhotoUser(photoUser);
        user.setRoles(roles);
        user.setEmail(email);

        userRepository.save(user);

        return user;

    }


}
