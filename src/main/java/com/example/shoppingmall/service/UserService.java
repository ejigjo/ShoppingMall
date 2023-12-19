package com.example.shoppingmall.service;

import com.example.shoppingmall.pojo.User;

public interface UserService {
    Integer register(User user);

    User findUserById(Integer userId);
}
