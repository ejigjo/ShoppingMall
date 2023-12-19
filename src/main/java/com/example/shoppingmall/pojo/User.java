package com.example.shoppingmall.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private Date createdDate;
    private Date lastModifiedDate;
}
