package com.java.ecommerce.basketservice.service;

import com.java.ecommerce.basketservice.client.PlatziStoreClient;
import com.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public List<PlatziProductResponse> getAllProducts() {
        return platziStoreClient.getAllProducts();
    }
    public PlatziProductResponse  getProductById(Long id) {
return platziStoreClient.getProductbById(id);
    }
}
