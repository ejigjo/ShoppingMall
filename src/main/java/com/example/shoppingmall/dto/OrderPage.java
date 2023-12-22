package com.example.shoppingmall.dto;

import com.example.shoppingmall.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPage {
    private Integer limit;
    private Integer offset;
    private Integer count;
    private List<Order> orderList;

}
