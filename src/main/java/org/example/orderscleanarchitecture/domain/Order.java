package org.example.orderscleanarchitecture.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Order(Long id, List<OrderItem> items, BigDecimal total) {

    public Order {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(items, "items");
        Objects.requireNonNull(total, "total");
        items = List.copyOf(items); // imutabilidade de coleção
    }

    public static Order empty(Long id) {
        return new Order(id, List.of(), BigDecimal.ZERO);
    }

    public Order addItem(OrderItem item) {
        var newItems = new ArrayList<>(items);        //Cria uma nova lista copiando os itens atuais.
        newItems.add(item);
        /*
        System.out.println("\n(Arrays.toString(newItems.toArray()))");
        System.out.println(Arrays.toString(newItems.toArray()));
        System.out.println("\n(newItems)");
        System.out.println(newItems);
        System.out.println("\n(Linha-a-Linha)");
        for (int i = 0; i < newItems.size(); i++) {
            System.out.println(i + ": " + newItems.get(i));
        }
        System.out.println("\nFIM\n\n");
        */
        var newTotal = total.add(item.subtotal());
        return new Order(id, newItems, newTotal);
    }
}

/*
Onde está a imutabilidade nisso tudo?
Essa é a pergunta mais importante agora.
1 - Order
Order é imutável
Quando você faz:
      Order updated = current.addItem(...)

você não modifica current.
Você cria um novo Order.
Ou seja:
- current continua representando o estado antigo
- updated representa o estado novo
Isso é imutabilidade.

2 - OrderItem
OrderItem também é imutável
Ele é um record com validações no construtor.
Depois de criado, ninguém muda: sku, qty ou unitPrice.

3 - CopyOf
A lista de itens foi protegida
No construtor do Order:
      items = List.copyOf(items);
Isso impede mutação externa da coleção.
Sem isso, alguém poderia alterar a lista “por fora”.
*/