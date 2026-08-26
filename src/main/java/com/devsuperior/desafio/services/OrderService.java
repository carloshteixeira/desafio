package com.devsuperior.desafio.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.desafio.dto.OrderDTO;
import com.devsuperior.desafio.dto.OrderItemDTO;
import com.devsuperior.desafio.entities.Order;
import com.devsuperior.desafio.entities.OrderItem;
import com.devsuperior.desafio.entities.Product;
import com.devsuperior.desafio.entities.User;
import com.devsuperior.desafio.repositories.OrderItemRepository;
import com.devsuperior.desafio.repositories.OrderRepository;
import com.devsuperior.desafio.repositories.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {

        Order entity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        authService.validateSelfOrAdmin(entity.getClient().getId());

        return new OrderDTO(entity);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order entity = new Order();

        entity.setMoment(Instant.now());

        User user = authService.authenticated();
        entity.setClient(user);

        entity = orderRepository.save(entity);

        for (OrderItemDTO itemDto : dto.getItems()) {

            Product product = productRepository.getReferenceById(
                    itemDto.getProductId());

            OrderItem item = new OrderItem(
                    entity,
                    product,
                    itemDto.getQuantity(),
                    product.getPrice()
            );

            orderItemRepository.save(item);

            entity.getItems().add(item);
        }

        return new OrderDTO(entity);
    }
}