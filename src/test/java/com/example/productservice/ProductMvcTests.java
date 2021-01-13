package com.example.productservice;


import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    private String toJsonString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }


    long test1Id;
    @BeforeEach
    public void setup() throws Exception{
        Product productOne = new Product();
        productOne.setName("Test1");
        productOne.setCategory(Category.Book);
        productOne.setPrice(14.99);
        productOne.setQuantity(10);

        Product s1 = repository.save(productOne);
        test1Id=s1.getId();
        Product productTwo = new Product();
        productTwo.setName("Test2");
        productTwo.setCategory(Category.Book);
        productTwo.setPrice(10.99);
        productTwo.setQuantity(1);

        repository.save(productTwo);
    }
    @AfterEach
    public void delete() throws Exception{
        repository.deleteAll();
    }
    @Test
    public void Should_Return_All_Products_And_Status_200() throws Exception {
        this.mockMvc.perform(get("/api/products/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Test1")))
                .andExpect(jsonPath("$[1].name", is("Test2")));
    }
    @Test
    public void Should_Return_Single_Product_And_Status_200() throws Exception {
        this.mockMvc.perform(get("/api/products/"+test1Id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Test1")));
    }

    @Test
    public void Should_Create_Single_Product_And_Status_200() throws Exception {
        Product product = new Product();
        product.setName("Test3");
        product.setCategory(Category.Book);
        product.setPrice(10.99);
        product.setQuantity(1);

        this.mockMvc.perform(post("/api/products/")
                .contentType(APPLICATION_JSON).content(toJsonString(product)))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void Should_Not_Create_Existing_Product_And_Status_409() throws Exception {
        Product product = new Product();
        product.setName("Test1");
        product.setCategory(Category.Book);
        product.setPrice(10.99);
        product.setQuantity(1);

        this.mockMvc.perform(post("/api/products/")
                .contentType(APPLICATION_JSON).content(toJsonString(product)))
                .andDo(print())
                .andExpect(status().isConflict());

    }
    @Test
    public void Should_Delete_Product_And_Status_200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}",test1Id).contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
