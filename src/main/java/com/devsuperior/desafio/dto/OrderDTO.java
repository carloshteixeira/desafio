package com.devsuperior.desafio.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.desafio.entities.Order;
import com.devsuperior.desafio.entities.OrderItem;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private ClientDTO client;
    private List<OrderItemDTO> items = new ArrayList<>();
    private PaymentDTO payment;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, ClientDTO client) {
        this.id = id;
        this.moment = moment;
        this.client = client;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        client = new ClientDTO(entity.getClient());

        for (OrderItem item : entity.getItems()) {
            items.add(new OrderItemDTO(item));
        }

        if (entity.getPayment() != null) {
            payment = new PaymentDTO(entity.getPayment());
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public ClientDTO getClient() {
        return client;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public PaymentDTO getPayment() {
        return payment;
    }
}