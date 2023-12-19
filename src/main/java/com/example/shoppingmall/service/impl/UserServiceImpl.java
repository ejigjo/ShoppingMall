package com.example.shoppingmall.service.impl;


import com.example.shoppingmall.dao.UserDao;
import com.example.shoppingmall.pojo.User;
import com.example.shoppingmall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(User user) {
        User userByEmail = userDao.findUserByEmail(user);
        if (userByEmail != null) {

            log.warn("該帳號已經被註冊");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        return userDao.createUser(user);

    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByEmail(User user) {

        return userDao.findUserByEmail(user);

    }

    @Override
    public User login(User user) {

        User userByEmail = userDao.findUserByEmail(user);

        if (userByEmail == null) {
            log.warn("帳號尚未註冊");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!user.getPassword().equals(userByEmail.getPassword())) {
            log.warn("輸入密碼不相符");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userByEmail;
    }
}
