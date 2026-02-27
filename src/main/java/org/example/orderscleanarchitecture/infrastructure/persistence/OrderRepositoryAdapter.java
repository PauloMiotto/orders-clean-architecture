package org.example.orderscleanarchitecture.infrastructure.persistence;

import org.example.orderscleanarchitecture.domain.Order;
import org.example.orderscleanarchitecture.domain.port.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository jpa;
    private final OrderMapper mapper;

    public OrderRepositoryAdapter(SpringDataOrderRepository jpa, OrderMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        var saved = jpa.save(mapper.toEntity(order));
        return mapper.toDomain(saved);
    }
}