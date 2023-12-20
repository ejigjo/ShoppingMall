package com.example.shoppingmall.service;

// import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductInsert;
import com.example.shoppingmall.dto.ProductQueryParam;

import java.util.List;

public interface ProductService {

     Product getProductInfo(Integer productId);

     Integer insert(ProductInsert productInsert);

     void update(Integer productId, ProductInsert productInsert);

     void delete(Integer productId);

    //List<Product> findProductsInfo();

    List<Product> findProductsInfo(ProductQueryParam productQueryParam);

    int total(ProductQueryParam productQueryParam);
}
