package com.example.shoppingmall.pojo;

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
