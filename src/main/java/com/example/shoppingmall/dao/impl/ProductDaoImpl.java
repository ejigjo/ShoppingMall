package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate npst;

    public Product getProductInfo(Integer productId) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = npst.query(sql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return null;
        }
        return productList.get(0);

    }
}
