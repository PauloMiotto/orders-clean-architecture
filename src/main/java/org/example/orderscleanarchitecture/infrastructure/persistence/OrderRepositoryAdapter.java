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

/*
Etapa 5 — Repository Adapter converte domínio para entity
O adapter recebe um Order (imutável, puro) e converte para OrderEntity (JPA, mutável).
Então ele chama:
      jpa.save(...)

Esse ponto é essencial:
- o domínio não conhece JPA
- o JPA não entra no domínio
- a conversão fica na infraestrutura


Etapa 6 — Hibernate/JPA persiste no H2
O Hibernate pega a entity e gera SQL por trás.
Ele salva em:
- ORDERS
- ORDER_ITEMS
E você confirmou isso no H2 Console.


Para abrir o H2:
      http://localhost:8080/h2-console

      Na tela do H2
      Driver Class: org.h2.Driver
      JDBC URL dever ser: jdbc:h2:mem:ordersdb
      User Name: sa
      Password:
Clique em [Connect]


O QUE O ADAPTER FAZ?
O adapter faz a ponte entre os dois mundos
O OrderRepositoryAdapter implementa a interface do domínio, mas usa Spring Data/JPA por baixo.
Ou seja:
- para o domínio, ele parece um OrderRepository
- por dentro, ele usa Hibernate
Esse é o papel do adapter:
traduzir um mundo para o outro.
*/
