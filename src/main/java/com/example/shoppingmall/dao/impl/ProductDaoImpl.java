package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.pojo.ProductInsert;
import com.example.shoppingmall.rowMapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate npjt;

    public Product getProductInfo(Integer productId) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = npjt.query(sql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return null;
        }
        return productList.get(0);

    }

    @Override
    public Integer insert(ProductInsert productInsert) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description,created_date ,last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description,:createdDate, :lastModifiedDate)";

        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("productName", productInsert.getProductName());
        paramMap.addValue("category", productInsert.getCategory().name());
        paramMap.addValue("imageUrl", productInsert.getImageUrl());
        paramMap.addValue("price", productInsert.getPrice());
        paramMap.addValue("stock", productInsert.getStock());
        paramMap.addValue("description", productInsert.getDescription());
        paramMap.addValue("createdDate", new Date());
        paramMap.addValue("lastModifiedDate", new Date());

        //GeneratedKeyHolder實現類keyHolder用於接住新增數據的Key
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //新增數據庫數據,並接住新自增的Key
        npjt.update(sql, paramMap, keyHolder);

        //返回新數據的key
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(Integer productId, ProductInsert productInsert) {
        String sql = "update product set product_name = :productName,category=:category,image_url=:imageUrl,price = :price,stock = :stock,description = :description" +
                " where product_id = :productId";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("productId", productId);
        paramMap.addValue("productName", productInsert.getProductName());
        paramMap.addValue("category", productInsert.getCategory().name());
        paramMap.addValue("imageUrl", productInsert.getImageUrl());
        paramMap.addValue("price", productInsert.getPrice());
        paramMap.addValue("stock", productInsert.getStock());
        paramMap.addValue("description", productInsert.getDescription());
        paramMap.addValue("lastModifiedDate", new Date());

        npjt.update(sql, paramMap);

    }

    @Override
    public void delete(Integer productId) {
        String sql = "delete from product where product_id = :productId";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("productId", productId);
        npjt.update(sql, sqlParam);
    }

    @Override
    public List<Product> findProductsInfo(Product product) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where 1=1";

        Map<String, Object> map = new HashMap<>();

        if (product.getCategory() != null) {
            sql = sql + " and category = :category";
            map.put("category", product.getCategory().name());
        }
        if (product.getProductName() != null) {
            sql = sql + " and product_name like :name";
            map.put("name", "%" + product.getProductName() + "%");
        }
        //排序
        sql = sql + " order by " +product.getOderBy()+" "+product.getSort();
        //分頁
        sql = sql + " limit " + product.getLimit() + " " + "offset "+product.getOffset();

        List<Product> productList = npjt.query(sql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return null;
        }

        return productList;
    }
}
