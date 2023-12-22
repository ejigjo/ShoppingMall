package com.example.shoppingmall.dao;


import com.example.shoppingmall.dto.OrderQuertParam;
import com.example.shoppingmall.pojo.Order;
import com.example.shoppingmall.pojo.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, int totalAmount);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);

    Order findOrderById(Integer orderId);

    List<OrderItem> findOrderItemById(Integer orderId);

    List<Order> findOrderInfo(OrderQuertParam orderQuertParam);

    Integer countOrder(OrderQuertParam orderQuertParam);
}
