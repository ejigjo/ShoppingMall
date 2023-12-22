package com.example.shoppingmall.controller;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.dto.OrderPage;
import com.example.shoppingmall.dto.OrderQuertParam;
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

import java.util.List;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Order> createOrder(
            @PathVariable Integer userId,
            @RequestBody @Valid RequestCreateOrder requestCreateOrder) {
        Integer orderId = orderService.createOrder(userId, requestCreateOrder);

        Order orderInfo = orderService.findOrderById(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(orderInfo);
    }
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<OrderPage> findOrderInfo(
            @RequestParam(required = false) Integer userId,
            @RequestParam(defaultValue = "3") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        OrderQuertParam orderQuertParam = new OrderQuertParam(userId, limit, offset);
        List<Order> orderList = orderService.findOrderInfo(orderQuertParam);

        Integer countOrder = orderService.countOrder(orderQuertParam);

        return ResponseEntity.status(HttpStatus.OK).body(new OrderPage(limit,offset,countOrder,orderList));
    }

}
