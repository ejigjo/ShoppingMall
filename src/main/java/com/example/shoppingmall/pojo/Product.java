package com.example.shoppingmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Integer productId;
    private String productName;
    private String category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}
