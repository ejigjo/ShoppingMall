package com.example.shoppingmall.service.impl;

import com.example.shoppingmall.dao.OrderDao;
import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.dao.UserDao;
import com.example.shoppingmall.dto.BuyItem;
import com.example.shoppingmall.dto.OrderQuertParam;
import com.example.shoppingmall.dto.RequestCreateOrder;
import com.example.shoppingmall.pojo.Order;
import com.example.shoppingmall.pojo.OrderItem;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.service.OrderService;
import com.example.shoppingmall.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ProductDao productDao;


    @Transactional
    @Override
    public Integer createOrder(Integer userId, RequestCreateOrder requestCreateOrder) {
        //確認user是否存在
        if (userDao.findUserById(userId) == null){
            log.warn("用戶id為{}不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<OrderItem> orderItemList = new ArrayList<>();
        int totalAmount = 0;
        for (BuyItem buyItem : requestCreateOrder.getBuyItemList()) {

            Product productInfo = productDao.getProductInfo(buyItem.getProductId());
            //確認該商品是否存在
            if (productInfo == null){
                log.warn("沒有{}這個品項",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                //確認庫存是否足夠
            }else if (productInfo.getStock()< buyItem.getQuantity()){
                log.warn("商品{}庫存不足,想購買數量{},剩餘數量{}",
                        buyItem.getProductId(),buyItem.getQuantity(),productInfo.getStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(productInfo.getProductId(),productInfo.getStock()-buyItem.getQuantity());

            //計算使用者購買的每項商品加總總金額
            int amount = productInfo.getPrice() * buyItem.getQuantity();
            totalAmount = totalAmount + amount;

            //將使用者下單的訂單內容加到orderItem裡面
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
        //藉由orderId尋找order資料
        Order orderInfo = orderDao.findOrderById(orderId);
        //藉由orderId尋找orderItem資料
        List<OrderItem> orderItemList = orderDao.findOrderItemById(orderId);
        //將兩筆數據合併到order class裡面返回給前端展示
        orderInfo.setOrderItemList(orderItemList);

        return orderInfo;


    }

    @Override
    public List<Order> findOrderInfo(OrderQuertParam orderQuertParam) {
        return orderDao.findOrderInfo(orderQuertParam);
    }

    @Override
    public Integer countOrder(OrderQuertParam orderQuertParam) {
       Integer countOrder = orderDao.countOrder(orderQuertParam);
        return countOrder;
    }
}
