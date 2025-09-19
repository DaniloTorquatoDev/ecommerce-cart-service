package com.java.ecommerce.basketservice.controller;

import com.java.ecommerce.basketservice.controller.request.BasketRequest;
import com.java.ecommerce.basketservice.entity.Basket;
import com.java.ecommerce.basketservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;



    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<Basket> creatBasket(@RequestBody BasketRequest basketRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(basketService.creatBasket(basketRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket (@PathVariable String id, @RequestBody BasketRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(basketService.updateBasket(id, request));
    }



}
