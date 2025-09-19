package com.java.ecommerce.basketservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "basket")
public class Basket {
    private String id;

    private Long client;

    private BigDecimal totalPrice;

    private List<Product> prodcts;

    private Status status;


    public void calculateTotalPrice() {
        this.totalPrice = prodcts.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
