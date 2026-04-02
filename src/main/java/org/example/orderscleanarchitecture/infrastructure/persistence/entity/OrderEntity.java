package org.example.orderscleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity //Diz que essa classe é uma entidade persistente. Sem essa anotação: a classe seria apenas uma classe Java comum.
@Table(name = "orders") //Essa anotação diz em qual tabela do banco essa entidade será mapeada. Aqui: a classe OrderEntity será associada à tabela chamada "orders".
public class OrderEntity { //“OrderEntity é o modelo do pedido do ponto de vista do banco.” ou ainda: “Essa classe diz ao Hibernate como salvar e carregar pedidos e seus itens.”


    @Id //Essa anotação marca o campo que será a chave primária da entidade.
    private Long id;

    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; }
}
