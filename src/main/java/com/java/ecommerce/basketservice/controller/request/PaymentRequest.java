package com.java.ecommerce.basketservice.controller.request;

import com.java.ecommerce.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private PaymentMethod paymentMethod;
}
