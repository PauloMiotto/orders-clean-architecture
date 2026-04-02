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

/*
O FLUXO COMPLETO DA REQUISIÇÃO
Quando você faz:
      POST /demo/orders/1/items?sku=ABC&qty=2&unitPrice=10.50
o fluxo, hoje, é mais ou menos este:

Etapa 1 — Controller recebe a requisição
      @PostMapping("/orders/{id}/items")
      public Order addItem(...)

O Spring faz automaticamente:
- lê o id da URL
- lê sku, qty, unitPrice dos query params
- converte tudo para tipos Java (Long, int, BigDecimal)
Depois chama seu metodo do controller.


Etapa 2 — Controller delega para o Application Service
      return orderService.addItem(id, sku, qty, unitPrice);

O controller não implementa regra de negócio.
Ele só:
- recebe HTTP
- traduz para chamada Java
- devolve resposta
Isso é importante porque controller não deve virar “o cérebro” do sistema.
*/

