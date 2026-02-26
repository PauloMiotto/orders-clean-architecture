package org.example.orderscleanarchitecture.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Order(Long id, List<OrderItem> items, BigDecimal total) {

    public Order {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(items, "items");
        Objects.requireNonNull(total, "total");
        items = List.copyOf(items); // imutabilidade de coleção
    }

    public static Order empty(Long id) {
        return new Order(id, List.of(), BigDecimal.ZERO);
    }

    public Order addItem(OrderItem item) {
        var newItems = new ArrayList<>(items);
        newItems.add(item);
        var newTotal = total.add(item.subtotal());
        return new Order(id, newItems, newTotal);
    }
}