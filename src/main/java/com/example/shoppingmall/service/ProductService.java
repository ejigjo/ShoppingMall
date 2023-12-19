package com.example.shoppingmall.service;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;

import java.util.List;

public interface ProductService {

     Product getProductInfo(Integer productId);

     Integer insert(ProductInsert productInsert);

     void update(Integer productId, ProductInsert productInsert);

     void delete(Integer productId);

    //List<Product> findProductsInfo();

    List<Product> findProductsInfo(ProductCategory productCategory, String name);
}
