package com.example.shoppingmall.service.impl;


import com.example.shoppingmall.dao.UserDao;
import com.example.shoppingmall.pojo.User;
import com.example.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(User user) {
        return userDao.createUser(user);

    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }
}
