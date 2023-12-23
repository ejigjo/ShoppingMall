package com.example.shoppingmall.service.impl;

import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductRequest;
import com.example.shoppingmall.dto.ProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.shoppingmall.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductInfo(Integer productId) {
        return productDao.getProductInfo(productId);
    }

    @Override
    public Integer insert(ProductRequest productRequest) {
        return productDao.insert(productRequest);
    }

    @Override
    public void update(Integer productId, ProductRequest productRequest) {
        productDao.update(productId, productRequest);
    }

    @Override
    public void delete(Integer productId) {
        productDao.delete(productId);
    }

    @Override
    public List<Product> findProductsInfo(ProductQueryParam productQueryParam) {
        return productDao.findProductsInfo(productQueryParam);
    }

    @Override
    public int total(ProductQueryParam productQueryParam) {
        int total = productDao.total(productQueryParam);
        return total;
    }
}
