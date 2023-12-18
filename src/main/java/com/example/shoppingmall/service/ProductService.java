package com.example.shoppingmall.service;

import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;

public interface ProductService {

     Product getProductInfo(Integer productId);

     Integer insert(ProductInsert productInsert);

     void update(Integer productId, ProductInsert productInsert);
}
