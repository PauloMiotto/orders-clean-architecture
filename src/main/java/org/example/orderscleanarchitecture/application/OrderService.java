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

    public OrderService(OrderRepository orders) { //Injeção por construtor
        this.orders = orders;
    }

    @Transactional
    public Order addItem(Long orderId, String sku, int qty, BigDecimal unitPrice) {
        Order current = orders.findById(orderId).orElseGet(() -> Order.empty(orderId));
        Order updated = current.addItem(new OrderItem(sku, qty, unitPrice));
        return orders.save(updated);
    }
}


/*
Etapa 3 — Application decide o caso de uso
      Order current = orders.findById(orderId).orElseGet(() -> Order.empty(orderId));
      Order updated = current.addItem(new OrderItem(sku, qty, unitPrice));
      return orders.save(updated);

Aqui está o caso de uso: “Adicionar item a um pedido”
O service faz a orquestração:
1. busca o pedido
2. se não existir, cria um vazio
3. cria um OrderItem
4. pede ao domínio para adicionar
5. salva o resultado


Etapa 4 — O domínio faz a regra
Aqui está a parte mais importante conceitualmente:
      Order updated = current.addItem(...)

Quem sabe como um pedido adiciona item é o próprio domínio.
Não é o controller.
Não é o JPA.
Não é o banco.
É a classe Order.
Isso é modelagem boa.
*/

