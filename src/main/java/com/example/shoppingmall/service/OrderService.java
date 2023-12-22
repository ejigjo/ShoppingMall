package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.OrderQuertParam;
import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.pojo.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, RequestCreateOrder requestCreateOrder);

    Order findOrderById(Integer orderId);

    List<Order> findOrderInfo(OrderQuertParam orderQuertParam);

    Integer countOrder(OrderQuertParam orderQuertParam);
}
