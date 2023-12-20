package com.example.shoppingmall.dao;


import com.example.shoppingmall.pojo.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, int totalAmount);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
