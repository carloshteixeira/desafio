package com.devsuperior.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafio.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
