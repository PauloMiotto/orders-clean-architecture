package org.example.orderscleanarchitecture.infrastructure.persistence;

import org.example.orderscleanarchitecture.domain.Order;
import org.example.orderscleanarchitecture.domain.OrderItem;
import org.example.orderscleanarchitecture.infrastructure.persistence.entity.OrderEntity;
import org.example.orderscleanarchitecture.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class OrderMapper {

    public Order toDomain(OrderEntity e) {
        var items = e.getItems().stream()
                .map(i -> new OrderItem(i.getSku(), i.getQty(), i.getUnitPrice()))
                .toList();

        BigDecimal total = e.getTotal() == null ? BigDecimal.ZERO : e.getTotal();
        return new Order(e.getId(), items, total);
    }

    public OrderEntity toEntity(Order d) {
        var e = new OrderEntity();
        e.setId(d.id());
        e.setTotal(d.total());

        var itemEntities = new ArrayList<OrderItemEntity>();
        for (var item : d.items()) {
            var ie = new OrderItemEntity();
            ie.setSku(item.sku());
            ie.setQty(item.qty());
            ie.setUnitPrice(item.unitPrice());
            ie.setOrder(e);
            itemEntities.add(ie);
        }
        e.setItems(itemEntities);

        return e;
    }
}