package com.example.shoppingmall.controller;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;
import com.example.shoppingmall.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Object> insert(@RequestBody @Valid ProductInsert productInsert) {
        Integer productId = productService.insert(productInsert);
        Product productInfo = productService.getProductInfo(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productInfo);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Object> update(@PathVariable Integer productId,
                                         @RequestBody ProductInsert productInsert) {
        Product productInfo = productService.getProductInfo(productId);
        if (productInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.update(productId, productInsert);

        Product updateInfo = productService.getProductInfo(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateInfo);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Object> delete(@PathVariable Integer productId) {
        productService.delete(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProductsInfo(
            @RequestParam(required = false) ProductCategory productCategory,
            @RequestParam(required = false) String name) {
        List<Product> productsList = productService.findProductsInfo(productCategory,name);

        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

}
