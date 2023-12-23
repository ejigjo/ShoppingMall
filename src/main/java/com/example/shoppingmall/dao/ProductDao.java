package com.example.shoppingmall.dao;


import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductRequest;
import com.example.shoppingmall.dto.ProductQueryParam;

import java.util.List;

public interface ProductDao {

    Product getProductInfo(Integer productId);

    Integer insert(ProductRequest productRequest);

    void update(Integer productId, ProductRequest productRequest);

    void delete(Integer productId);

    //List<Product> findProductsInfo();

    List<Product> findProductsInfo(ProductQueryParam productQueryParam);

    int total(ProductQueryParam productQueryParam);

    void updateStock(Integer productId, Integer stock);
}
