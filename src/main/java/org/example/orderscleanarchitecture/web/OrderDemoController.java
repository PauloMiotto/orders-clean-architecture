package org.example.orderscleanarchitecture.web;

import org.example.orderscleanarchitecture.domain.Order;
import org.example.orderscleanarchitecture.domain.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/demo")
public class OrderDemoController {

    @PostMapping("/orders/{id}/items")
    public Order addItem(@PathVariable Long id,
                         @RequestParam String sku,
                         @RequestParam int qty,
                         @RequestParam BigDecimal unitPrice) {

        Order order = Order.empty(id);
        Order updated = order.addItem(new OrderItem(sku, qty, unitPrice));

        // aqui você pode botar breakpoint e ver:
        // order != updated (novo objeto)
        return updated;
    }
}