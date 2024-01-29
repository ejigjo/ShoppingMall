package com.example.shoppingmall.controller;

import com.example.shoppingmall.constant.ProductCategory;
import com.example.shoppingmall.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test// 查詢商品測試
    public void getProduct_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("蘋果（澳洲）"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FOOD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdDate").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastModifiedDate").value(Matchers.notNullValue()));

    }

    @Test//沒有查詢到商品測試
    public void getProduct_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }


    @Transactional
    @Test// 創建商品成功測試
    public void createProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageUrl("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())//標記
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("test food product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FOOD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdDate").value(Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastModifiedDate").value(Matchers.notNullValue()));

    }

    @Transactional
    @Test//創建商品時參數不合法測試
    public void createProduct_illegalArgument() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }


    @Transactional
    @Test// 更新商品成功測試
    public void updateProduct_success() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageUrl("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("test food product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("FOOD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("http://test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdDate").value(notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastModifiedDate").value(notNullValue()));

    }

    @Transactional
    @Test//更新商品參數不合法時測試
    public void updateProduct_illegalArgument() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));

    }

    @Transactional
    @Test//更新商品不存在測試
    public void updateProduct_productNotFound() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("test food product");
        productRequest.setCategory(ProductCategory.FOOD);
        productRequest.setImageUrl("http://test.com");
        productRequest.setPrice(100);
        productRequest.setStock(2);

        String json = objectMapper.writeValueAsString(productRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/products/{productId}", 20000)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }


    @Transactional
    @Test// 刪除商品成功測試
    public void deleteProduct_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/{productId}", 5);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Transactional
    @Test//刪除商品不存在測試
    public void deleteProduct_deleteNonExistingProduct() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/{productId}", 20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }


    @Test// 查詢商品列表
    public void getProducts() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.limit", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.offset", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList",hasSize(3)));

    }

    @Test//條件查詢測試
    public void getProducts_filtering() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("search", "B")
                .param("category", "CAR");

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.limit", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.offset", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList", hasSize(3)));

    }

    @Test//商品展示排序測試
    public void getProducts_sorting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("orderBy", "price")
                .param("sort", "desc");

        ResultActions resultActions = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.limit", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.offset", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[0].productId", equalTo(7)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[1].productId", equalTo(6)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[2].productId", equalTo(5)));
    }

    @Test//商品展示測試
    public void getProducts_pagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products")
                .param("limit", "2")
                .param("offset", "2");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.limit", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.offset", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[0].productId", equalTo(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productList[1].productId", equalTo(4)));
   }
}