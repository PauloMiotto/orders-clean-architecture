package org.example.orderscleanarchitecture.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private int qty;
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public Long getId() { return id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }
}