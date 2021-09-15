package com.example.projetMedical.api;


import com.example.projetMedical.model.entities.UsersEntity;
import com.example.projetMedical.model.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("api/users")
public class UserControllerApi {
    //test
    private UserService usersService;

    public UserControllerApi(UserService usersService)
    {
        this.usersService = usersService;
    }


    @GetMapping(path = "", produces = "application/json")
    public List<UsersEntity> getUserListApi() {
        return usersService.getAllUsers();
    }


    @GetMapping(path= "/{id}", produces = "application/json")
    public UsersEntity getUserById(@PathVariable("id") int id) {
        Optional<UsersEntity> userOptional = usersService.getUserById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user unknown");
        }

    }

    @PostMapping (path="", produces = "application/json")
    public ResponseEntity<UsersEntity> addUserApi(@RequestBody UsersEntity userInput) {
        usersService.addUser(userInput.getName(),userInput.getEmail(),userInput.getPassword(), userInput.getRoles(), userInput.getPhotoUser());
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        usersService.addUser(
                        userInput.getName(),
                        userInput.getEmail(),
                        userInput.getPassword(),
                        userInput.getRoles(),
                        userInput.getPhotoUser()));

    }

}


