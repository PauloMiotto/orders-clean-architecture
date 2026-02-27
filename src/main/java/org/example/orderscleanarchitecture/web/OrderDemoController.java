package org.example.orderscleanarchitecture.web;

import org.example.orderscleanarchitecture.application.OrderService;
import org.example.orderscleanarchitecture.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/demo")
public class OrderDemoController {

    private final OrderService orderService;

    public OrderDemoController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/{id}/items")
    public Order addItem(@PathVariable Long id,
                         @RequestParam String sku,
                         @RequestParam int qty,
                         @RequestParam BigDecimal unitPrice) {

        return orderService.addItem(id, sku, qty, unitPrice);
    }
}