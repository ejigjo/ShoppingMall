package com.example.shoppingmall.pojo;

import com.example.shoppingmall.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInsert {
    @NotNull
    private String productName;
    @NonNull
    private ProductCategory category;
    @NonNull
    private String imageUrl;
    @NonNull
    private Integer price;
    @NonNull
    private Integer stock;
    @NonNull
    private String description;
}
