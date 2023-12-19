package com.example.shoppingmall.controller;

import com.example.shoppingmall.pojo.User;
import com.example.shoppingmall.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {

        Integer userId = userService.register(user);

        User userInfo = userService.findUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);

    }
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid User user){

       User userLogin = userService.login(user);
        log.info("登錄成功");
        return ResponseEntity.status(HttpStatus.OK).body(userLogin);
    }
}
