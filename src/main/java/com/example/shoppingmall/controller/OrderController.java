package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService oderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(
            @PathVariable Integer userId,
            @RequestBody @Valid RequestCreateOrder requestCreateOrder) {
            Integer orderId = oderService.createOrder(userId,requestCreateOrder);
        return  ResponseEntity.status(HttpStatus.OK).body(orderId);
    }

}
