package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.pojo.Order;
import com.example.shoppingmall.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class OrderController {
    @Autowired
    OrderService oderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Order> createOrder(
            @PathVariable Integer userId,
            @RequestBody @Valid RequestCreateOrder requestCreateOrder) {
            Integer orderId = oderService.createOrder(userId,requestCreateOrder);
      log.info("有沒有ID:{}",orderId);
           Order orderInfo = oderService.findOrderById(orderId);

        return  ResponseEntity.status(HttpStatus.OK).body(orderInfo);
    }

}
