package com.example.shoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuyItem {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

}
