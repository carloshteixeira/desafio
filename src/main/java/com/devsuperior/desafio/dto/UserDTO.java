package com.devsuperior.desafio.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.desafio.entities.Role;
import com.devsuperior.desafio.entities.User;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();

        for (Role role : entity.getRoles()) {
            roles.add(role.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getRoles() {
        return roles;
    }
}