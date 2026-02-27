package org.example.orderscleanarchitecture.infrastructure.persistence;

import org.example.orderscleanarchitecture.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {}