package com.example.shoppingmall.dao.impl;

import com.example.shoppingmall.dao.UserDao;
import com.example.shoppingmall.dto.UserLoginRequest;
import com.example.shoppingmall.dto.UserRegisterRequest;
import com.example.shoppingmall.pojo.User;
import com.example.shoppingmall.rowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Integer createUser(UserRegisterRequest user) {
        String sql = "insert into user(email, password, created_date, last_modified_date) " +
                "VALUES(:email,:password,:createdDate,:lastModifiedDate)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", user.getEmail());
        mapSqlParameterSource.addValue("password", user.getPassword());
        mapSqlParameterSource.addValue("createdDate", new Date());
        mapSqlParameterSource.addValue("lastModifiedDate", new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        npjt.update(sql, mapSqlParameterSource, keyHolder);


        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public User findUserById(Integer userId) {

        String sql = "select user_id, email, password, created_date, last_modified_date from user where user_id = :userId";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userId", userId);
        List<User> userList = npjt.query(sql,mapSqlParameterSource, new UserRowMapper());

        return userList.get(0);
    }

    @Override
    public User findUserByEmail(String email) {

        String sql = "select user_id, email, password, created_date, last_modified_date from user where email = :email";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);
        List<User> userByEmail = npjt.query(sql,mapSqlParameterSource, new UserRowMapper());
        if (userByEmail.isEmpty()){
            return null;
        }
        return userByEmail.get(0);

    }


}
