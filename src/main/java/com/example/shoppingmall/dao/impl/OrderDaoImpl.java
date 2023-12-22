package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.dao.OrderDao;
import com.example.shoppingmall.dto.OrderQuertParam;
import com.example.shoppingmall.pojo.Order;
import com.example.shoppingmall.pojo.OrderItem;
import com.example.shoppingmall.rowMapper.OrderItemRowMapper;
import com.example.shoppingmall.rowMapper.OrderRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
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
        npjt.update(sql, sqlParam);

    }

    @Override
    public Order findOrderById(Integer orderId) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date " +
                "from `order` where order_id = :orderId";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("orderId", orderId);


        List<Order> orderList = npjt.query(sql, sqlParam, new OrderRowMapper());
        return orderList.get(0);
    }

    @Override
    public List<OrderItem> findOrderItemById(Integer orderId) {
        String sql = "select oi.order_item_id,oi.order_id,oi.product_id,oi.quantity,oi.amount,p.product_name,p.image_url\n" +
                "from order_item as oi left join product as p on oi.product_id = p.product_id where oi.order_id = :orderId";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        sqlParam.addValue("orderId", orderId);
        return npjt.query(sql, sqlParam, new OrderItemRowMapper());
    }

    @Override
    public List<Order> findOrderInfo(OrderQuertParam orderQuertParam) {
        String sql = "select order_id, user_id, total_amount, created_date,last_modified_date from `order` where 1=1";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();

        sql = sql + " order by created_date desc";

        sql = sql + " limit :limit offset :offset";

        sqlParam.addValue("limit", orderQuertParam.getLimit());
        sqlParam.addValue("offset", orderQuertParam.getOffset());

        List<Order> orderList = npjt.query(sql, sqlParam, new OrderRowMapper());

        return orderList;
    }

    @Override
    public Integer countOrder(OrderQuertParam orderQuertParam) {
        String sql = "select order_id, user_id, total_amount, created_date, last_modified_date from `order`";
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        List<Order> orderList = npjt.query(sql, sqlParam, new OrderRowMapper());

        return orderList.size();
    }
}
