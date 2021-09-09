package com.example.projetMedical.model.configuration;


import com.example.projetMedical.model.services.UserService;
import com.example.projetMedical.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

    @Autowired // injection de dépendance
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
        if (userRepository.count() ==0) {

            userService.addUser("Admin", "admin@admin.com", "1234", "ROLE_ADMIN", "admin.png");

        }

        };

    }


}
