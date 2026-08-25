package com.devsuperior.desafio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.desafio.dto.UserDTO;
import com.devsuperior.desafio.entities.User;
import com.devsuperior.desafio.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe(Authentication authentication) {

        String email = authentication.getName();

        User entity = repository.findByEmail(email);

        UserDTO dto = new UserDTO(entity);

        return ResponseEntity.ok(dto);
    }
}
