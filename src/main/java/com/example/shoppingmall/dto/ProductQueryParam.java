package com.example.shoppingmall.dto;


import com.example.shoppingmall.constant.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductQueryParam {
    private String productName;
    private ProductCategory category;
    private String oderBy;
    private String sort;
    private Integer limit;
    private Integer offset;
}
