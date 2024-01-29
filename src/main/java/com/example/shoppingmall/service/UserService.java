package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.UserLoginRequest;
import com.example.shoppingmall.dto.UserRegisterRequest;
import com.example.shoppingmall.pojo.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User findUserById(Integer userId);



    User findUserByEmail(UserRegisterRequest user);

    User login(UserLoginRequest userLoginRequest);
}
