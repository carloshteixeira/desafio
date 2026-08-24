package com.devsuperior.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.desafio.entities.OrderItem;
import com.devsuperior.desafio.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
