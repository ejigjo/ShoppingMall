package com.example.shoppingmall.dao;

import com.example.shoppingmall.dto.UserLoginRequest;
import com.example.shoppingmall.dto.UserRegisterRequest;
import com.example.shoppingmall.pojo.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User findUserById(Integer userId);

    User findUserByEmail(String email);


}
