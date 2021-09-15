package com.example.projetMedical.model.services;


import com.example.projetMedical.model.entities.UsersEntity;
import com.example.projetMedical.model.repositories.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
//test

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersEntity> getAllUsers() {

        return (List<UsersEntity>) userRepository.findAll();
    }

    public Optional<UsersEntity> getUserById(int id) {
        return userRepository.findById(id);  // optional gere une éventualité
    }

    public UsersEntity setAndSaveUser(UsersEntity user, String name, String password, String photoUser, String roles, String email) {
        user.setName(name);
        user.setPassword(password);
        user.setPhotoUser(photoUser);
        user.setRoles(roles);
        user.setEmail(email);

        userRepository.save(user);
        return user;

    }

    @Transactional
    // CREATE USER
    public UsersEntity addUser(String name, String email, String password, String roles, String photoUser) {

        UsersEntity user = new UsersEntity();
        return setAndSaveUser(user, name, email, password, roles, photoUser);

    }


    @Transactional
    public UsersEntity updateUserById(int id, String name, String email, String password, String roles, String photoUser) {
        Optional<UsersEntity> userOptional = getUserById(id);

        if (userOptional.isPresent()) {
            return setAndSaveUser(userOptional.get(), name, email, password, roles, photoUser);
        } else {
            throw new ObjectNotFoundException(id,"user unknown");
        }

    }


    @Transactional
    public void deleteUserById(int id) {
        Optional<UsersEntity> userOptional = getUserById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "user unknown");

        }
    }

}