package com.example.shoppingmall.dao;

import com.example.shoppingmall.pojo.User;

public interface UserDao {
    Integer createUser(User user);

    User findUserById(Integer userId);

    User findUserByEmail(User user);


}
