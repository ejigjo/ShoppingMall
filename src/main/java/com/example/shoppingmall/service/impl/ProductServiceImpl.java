package com.example.shoppingmall.service.impl;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;
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
    public Integer insert(ProductInsert productInsert) {
        return productDao.insert(productInsert);
    }

    @Override
    public void update(Integer productId, ProductInsert productInsert) {
        productDao.update(productId,productInsert);
    }

    @Override
    public void delete(Integer productId) {
        productDao.delete(productId);
    }

    @Override
    public List<Product> findProductsInfo(Product product) {
        return productDao.findProductsInfo(product);
    }
}
