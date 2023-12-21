package com.example.shoppingmall.rowMapper;

import com.example.shoppingmall.pojo.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order orderInfo = new Order();
        orderInfo.setOrderId(rs.getInt("order_id"));
        orderInfo.setUserId(rs.getInt("user_id"));
        orderInfo.setTotalAmount(rs.getInt("total_amount"));
        orderInfo.setCreatedDate(rs.getTimestamp("created_date"));
        orderInfo.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        return orderInfo;
    }
}
