package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.RequestCreateOrder;

public interface OrderService {
    Integer createOrder(Integer userId, RequestCreateOrder requestCreateOrder);
}
