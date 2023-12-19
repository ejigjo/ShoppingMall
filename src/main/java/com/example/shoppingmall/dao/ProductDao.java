package com.example.shoppingmall.dao;


import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;
import com.example.shoppingmall.pojo.ProductQueryParam;

import java.util.List;

public interface ProductDao {

    Product getProductInfo(Integer productId);

    Integer insert(ProductInsert productInsert);

    void update(Integer productId, ProductInsert productInsert);

    void delete(Integer productId);

    //List<Product> findProductsInfo();

    List<Product> findProductsInfo(ProductQueryParam productQueryParam);

    int total(ProductQueryParam productQueryParam);
}
