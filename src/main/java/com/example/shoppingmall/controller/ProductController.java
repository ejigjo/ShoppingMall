package com.example.shoppingmall.controller;

import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Object> getProductInfo(@PathVariable Integer productId) {
        Product productInfo = productService.getProductInfo(productId);
        if (productId == null) {
            //如果數據為null回傳NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //回傳響應數據200與產品資料
        return ResponseEntity.status(200).body(productInfo);

    }

}
