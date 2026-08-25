package com.devsuperior.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}