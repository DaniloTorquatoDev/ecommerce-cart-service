package com.java.ecommerce.basketservice.service;

import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import com.java.ecommerce.basketservice.controller.request.BasketRequest;
import com.java.ecommerce.basketservice.entity.Basket;
import com.java.ecommerce.basketservice.entity.Product;
import com.java.ecommerce.basketservice.entity.Status;
import com.java.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;


    public Basket getBasketById(String id) {
        return  basketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carrinho vazio!"));
    }


    public Basket creatBasket(BasketRequest basketRequest) {
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    try {
                        throw new IllegalAccessException("Ja existe uma basket aberta para esse cliente!");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });



        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest ->{
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());

            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .prodcts(products)
                .build();

        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }


    public  Basket updateBasket(String basketId, BasketRequest request) {
        Basket savedBasket = getBasketById(basketId);

        List<Product> products = new ArrayList<>();

        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
            products.add(Product.builder()
                            .id(platziProductResponse.id())
                            .title(platziProductResponse.title())
                            .price(platziProductResponse.price())
                            .quantity(productRequest.quantity())
                    .build());
        });
        savedBasket.setProdcts(products);

        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);

    }
}
