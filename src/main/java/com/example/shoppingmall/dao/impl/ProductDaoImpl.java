package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.dao.ProductDao;
import com.example.shoppingmall.pojo.Product;
import com.example.shoppingmall.dto.ProductRequest;
import com.example.shoppingmall.dto.ProductQueryParam;
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
    public int total(ProductQueryParam productQueryParam) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product";

        Map<String, Object> map = new HashMap<>();

        if (productQueryParam.getCategory() != null) {
            sql = sql + " and category = :category";
            map.put("category", productQueryParam.getCategory().name());
        }
        if (productQueryParam.getProductName() != null) {
            sql = sql + " and product_name like :name";
            map.put("name", "%" + productQueryParam.getProductName() + "%");
        }

        List<Product> productList = npjt.query(sql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return 0;
        }
        return productList.size();

    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "update product set stock = :stock,last_modified_date = :lastModifiedDate " +
                "where product_id = :productId";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("productId",productId);
        sqlParam.addValue("lastModifiedDate",new Date());
        sqlParam.addValue("stock",stock);

        npjt.update(sql,sqlParam);

    }


    @Override
    public Integer insert(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description,created_date ,last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description,:createdDate, :lastModifiedDate)";

        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("productName", productRequest.getProductName());
        paramMap.addValue("category", productRequest.getCategory().name());
        paramMap.addValue("imageUrl", productRequest.getImageUrl());
        paramMap.addValue("price", productRequest.getPrice());
        paramMap.addValue("stock", productRequest.getStock());
        paramMap.addValue("description", productRequest.getDescription());
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
    public void update(Integer productId, ProductRequest productRequest) {
        String sql = "update product set product_name = :productName,category=:category,image_url=:imageUrl,price = :price,stock = :stock,description = :description" +
                " where product_id = :productId";
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("productId", productId);
        paramMap.addValue("productName", productRequest.getProductName());
        paramMap.addValue("category", productRequest.getCategory().name());
        paramMap.addValue("imageUrl", productRequest.getImageUrl());
        paramMap.addValue("price", productRequest.getPrice());
        paramMap.addValue("stock", productRequest.getStock());
        paramMap.addValue("description", productRequest.getDescription());
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
    public List<Product> findProductsInfo(ProductQueryParam productQueryParam) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date " +
                "from product where 1=1";

        Map<String, Object> map = new HashMap<>();

        if (productQueryParam.getCategory() != null) {
            sql = sql + " and category = :category";
            map.put("category", productQueryParam.getCategory().name());
        }
        if (productQueryParam.getProductName() != null) {
            sql = sql + " and product_name like :name";
            map.put("name", "%" + productQueryParam.getProductName() + "%");
        }
        //排序
        sql = sql + " order by " + productQueryParam.getOderBy() + " " + productQueryParam.getSort();
        //分頁
        sql = sql + " limit " + productQueryParam.getLimit() + " " + "offset " + productQueryParam.getOffset();

        List<Product> productList = npjt.query(sql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return null;
        }

        return productList;
    }

}
