package org.example.orderscleanarchitecture.application;

import org.example.orderscleanarchitecture.domain.Order;
import org.example.orderscleanarchitecture.domain.OrderItem;
import org.example.orderscleanarchitecture.domain.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orders;

    public OrderService(OrderRepository orders) {
        this.orders = orders;
    }

    @Transactional
    public Order addItem(Long orderId, String sku, int qty, BigDecimal unitPrice) {
        Order current = orders.findById(orderId).orElseGet(() -> Order.empty(orderId));

        Order updated = current.addItem(new OrderItem(sku, qty, unitPrice));
        return orders.save(updated);
    }
}