package com.example.shoppingmall.dao;

import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;

public interface ProductDao {

    Product getProductInfo(Integer productId);

    Integer insert(ProductInsert productInsert);
}
