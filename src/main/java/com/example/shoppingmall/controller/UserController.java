package com.example.shoppingmall.controller;

import com.example.shoppingmall.pojo.User;
import com.example.shoppingmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        Integer userId = userService.register(user);

        User userInfo = userService.findUserById(userId);
        System.out.println(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);

    }
}
