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

/*
POR QUE O MAPPER EXISTE?
Porque Order e OrderEntity não são a mesma coisa.

1 - Order
- imutável
- puro
- sem JPA annotations
- representa domínio

2 - OrderEntity
- mutável
- com @Entity
- acoplado ao Hibernate
- representa persistência

3 - OrderMapper
O mapper existe para converter:
- Entity -> Domain
- Domain -> Entity
Isso evita poluir o domínio com detalhes técnicos.


POR QUE ENTÃO AS ENTIDADES SÃO MUTÁVEIS?
Excelente ponto.
Porque JPA/Hibernate trabalha melhor com objetos mutáveis.
Por exemplo:
      OrderEntity e = new OrderEntity();
      e.setId(...);
      e.setTotal(...);
      e.setItems(...);

O Hibernate espera esse estilo porque ele gerencia:
- ciclo de vida da entity
- dirty checking
- lazy loading
- proxies

Então a ideia moderna não é: “Tudo no sistema inteiro precisa ser imutável”
A ideia correta é: “O domínio deve ser o mais imutável possível; a infraestrutura pode ser mutável quando necessário”
Esse equilíbrio é muito importante.



*/