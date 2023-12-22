package com.example.shoppingmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuertParam {
    private Integer userId;
    private Integer limit;
    private Integer offset;
}
