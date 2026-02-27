package org.example.orderscleanarchitecture.domain.port;

import org.example.orderscleanarchitecture.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long id);
    Order save(Order order);
}