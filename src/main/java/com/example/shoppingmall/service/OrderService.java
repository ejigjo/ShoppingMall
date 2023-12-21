package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.pojo.Order;

public interface OrderService {
    Integer createOrder(Integer userId, RequestCreateOrder requestCreateOrder);

    Order findOrderById(Integer orderId);
}
