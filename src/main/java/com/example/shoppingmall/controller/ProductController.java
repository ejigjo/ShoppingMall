package com.example.shoppingmall.controller;

import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;
import com.example.shoppingmall.service.ProductService;
import com.example.shoppingmall.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.OK).body(productInfo);

    }
    @PostMapping("/products")
    public ResponseEntity<Object> insert(@RequestBody @Valid ProductInsert productInsert){
        Integer productId = productService.insert(productInsert);
        Product productInfo = productService.getProductInfo(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productInfo);
    }

}
