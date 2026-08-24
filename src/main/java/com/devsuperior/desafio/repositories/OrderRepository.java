package com.devsuperior.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafio.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}