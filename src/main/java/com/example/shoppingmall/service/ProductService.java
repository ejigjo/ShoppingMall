package com.example.shoppingmall.service;

// import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductRequest;
import com.example.shoppingmall.dto.ProductQueryParam;

import java.util.List;

public interface ProductService {

     Product getProductInfo(Integer productId);

     Integer insert(ProductRequest productRequest);

     void update(Integer productId, ProductRequest productRequest);

     void delete(Integer productId);

    //List<Product> findProductsInfo();

    List<Product> findProductsInfo(ProductQueryParam productQueryParam);

    int total(ProductQueryParam productQueryParam);
}
