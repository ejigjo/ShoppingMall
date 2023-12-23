package com.example.shoppingmall.controller;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductRequest;
import com.example.shoppingmall.dto.ProductPage;
import com.example.shoppingmall.dto.ProductQueryParam;
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
        //回傳響應數據OK與產品資料
        return ResponseEntity.status(HttpStatus.OK).body(productInfo);

    }

    @PostMapping("/products")
    public ResponseEntity<Object> insert(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.insert(productRequest);
        Product productInfo = productService.getProductInfo(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productInfo);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Object> update(@PathVariable Integer productId,
                                         @RequestBody ProductRequest productRequest) {
        Product productInfo = productService.getProductInfo(productId);
        if (productInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.update(productId, productRequest);

        Product updateInfo = productService.getProductInfo(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateInfo);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Object> delete(@PathVariable Integer productId) {
        productService.delete(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/products")
    public ResponseEntity<ProductPage> findProductsInfo(
            @RequestParam(required = false) ProductCategory productCategory,
            @RequestParam(required = false) String prouductName,
            @RequestParam(defaultValue = "created_date") String oderBy,
            @RequestParam(defaultValue = "desc") String sort,
            @RequestParam(defaultValue = "3") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setCategory(productCategory);
        productQueryParam.setProductName(prouductName);
        productQueryParam.setOderBy(oderBy);
        productQueryParam.setSort(sort);
        productQueryParam.setLimit(limit);
        productQueryParam.setOffset(offset);
        List<Product> productsList = productService.findProductsInfo(productQueryParam);
        int total = productService.total(productQueryParam);
        ProductPage productPage = new ProductPage(limit,offset,total,productsList);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }

}
