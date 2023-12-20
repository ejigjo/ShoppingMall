package com.example.shoppingmall.dto;

import com.example.shoppingmall.pojo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPage {
    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<Product> productList;
}
