package org.example.orderscleanarchitecture.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record OrderItem(String sku, int qty, BigDecimal unitPrice) {

    public OrderItem {
        Objects.requireNonNull(sku, "sku");
        Objects.requireNonNull(unitPrice, "unitPrice");
        if (sku.isBlank()) throw new IllegalArgumentException("sku vazio");
        if (qty <= 0) throw new IllegalArgumentException("qty deve ser > 0");
        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("unitPrice não pode ser negativo");
    }

    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(qty));
    }
}