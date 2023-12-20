package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.dao.OrderDao;
import com.example.shoppingmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Integer createOrder(Integer userId, int totalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date)" +
                " VALUES (:userId,:totalAmount,:createdDate,:lastModifiedDate)";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("userId", userId);
        sqlParam.addValue("totalAmount", totalAmount);
        sqlParam.addValue("createdDate", new Date());
        sqlParam.addValue("lastModifiedDate", new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        npjt.update(sql, sqlParam, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO order_item (order_id, product_id, quantity, amount)" +
                " VALUES (:orderId,:productId,:quantity, :amount)";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();

        for (OrderItem orderItem : orderItemList) {
            sqlParam.addValue("orderId", orderId);
            sqlParam.addValue("productId", orderItem.getProductId());
            sqlParam.addValue("quantity", orderItem.getQuantity());
            sqlParam.addValue("amount", orderItem.getAmount());
        }
        npjt.update(sql,sqlParam);

    }
}
