package com.example.shoppingmall.service.impl;

import com.example.shoppingmall.dao.OrderDao;
import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.dto.BuyItem;
import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.pojo.Order;
import com.example.shoppingmall.pojo.OrderItem;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.service.OrderService;
import com.example.shoppingmall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductService productService;
    @Autowired
    ProductDao productDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, RequestCreateOrder requestCreateOrder) {


        List<OrderItem> orderItemList = new ArrayList<>();
        int totalAmount = 0;
        for (BuyItem buyItem : requestCreateOrder.getBuyItemList()) {
            Integer productId1 = buyItem.getProductId();

            Integer productId = buyItem.getProductId();
            log.info("Processing product with ID: {}", productId);
            log.info("Processing product with ID: {}", productId);
            //獲取該商品金額
            Integer productPrice = productDao.getProductInfo(buyItem.getProductId()).getPrice();

            //計算使用者購買的每項商品加總總金額
            int amount = productPrice * buyItem.getQuantity();
            totalAmount = totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);


        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItem(orderId, orderItemList);


        return orderId;
    }

    @Override
    public Order findOrderById(Integer orderId) {
        Order orderInfo = orderDao.findOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.findOrderItemById(orderId);
        orderInfo.setOrderItemList(orderItemList);

        return orderInfo;


    }
}
