package com.example.productservice;

import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    private Product productOne;
    private Product productTwo;

    @BeforeEach
    public void init() {
        productOne = new Product();
        productOne.setName("The Body");
        productOne.setDescription("by Bill");
        productOne.setPrice(14.99);
        productOne.setQuantity(10);

        productTwo = new Product();
        productTwo.setName("Wild Game");
        productTwo.setDescription("by Brod");
        productTwo.setPrice(10.99);
        productTwo.setQuantity(1);

        entityManager.persist(productOne);
        entityManager.persist(productTwo);
    }

    @Test
    public void Should_Get_A_Single_Product() {
        Long idOne = (Long) entityManager.getId(productOne);
        Product product = repository.findById(idOne).get();

        assertThat(product.getName()).isEqualTo("The Body");
        assertThat(product.getDescription()).isEqualTo("by Bill");
        assertThat(product.getPrice()).isEqualTo(14.99);
    }

    @Test
    public void Should_Not_Get_Unknown_Product() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            var product = repository.findById(222L).get();
        });

    }

    @Test
    public void Should_Get_All_Products() {
        Long idOne = (Long) entityManager.getId(productOne);
        var productOne = repository.findById(idOne).get();

        Long idTwo = (Long) entityManager.getId(productOne);
        var productTwo = repository.findById(idOne).get();

        ArrayList products = (ArrayList<Product>) repository.findAll();
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.contains(productOne)).isTrue();
        assertThat(products.contains(productTwo)).isTrue();
    }

    @Test
    public void Should_Create_New_Product() {
        Product productThree = new Product();
        productThree.setName("Greenlights");
        productThree.setDescription("by Matthew");
        productThree.setPrice(24.99);
        productThree.setQuantity(4);

        Product createdProduct = repository.save(productThree);

        assertThat(createdProduct.getPrice()).isEqualTo(productThree.getPrice());
        assertThat(createdProduct.getDescription()).isEqualTo(productThree.getDescription());
        assertThat(createdProduct.getName()).isEqualTo(productThree.getName());
        assertThat(createdProduct.getQuantity()).isEqualTo(productThree.getQuantity());
    }

    @Test
    public void Should_Not_Create_Existing_Product() {
        Product productThree = new Product();
        productThree.setName("Greenlights");
        productThree.setDescription("by Matthew");
        productThree.setPrice(24.99);
        productThree.setQuantity(4);

        Product createdProduct = repository.save(productThree);

        Product productFour = new Product();
        productFour.setName("Greenlights");
        productFour.setPrice(24.99);
        productFour.setId(createdProduct.getId());

        repository.save(productFour);
    }

    @Test
    public void Should_Update_A_Product() {
        Long idOne = (Long) entityManager.getId(productOne);
        var productOne = repository.findById(idOne).get();

        productOne.setQuantity(1000);
        productOne.setName("Updated");
        var updatedProduct = repository.save(productOne);

        assertThat(updatedProduct.getQuantity()).isEqualTo(productOne.getQuantity());
        assertThat(updatedProduct.getName()).isEqualTo(productOne.getName());
    }

    @Test
    public void Should_Not_Update_Unknown_Product() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            var productOne = repository.findById(222L).get();
        });
    }

    @Test
    public void Should_Delete_A_Product() {
        Long idOne = (Long) entityManager.getId(productOne);
        var productOne = repository.findById(idOne).get();

        repository.delete(productOne);

        assertThat(((List<Product>) repository.findAll()).stream().count()).isEqualTo(1);
    }

    @Test
    public void Should_Delete_All_Products() {

        repository.deleteAll();

        assertThat(((List<Product>) repository.findAll()).stream().count()).isEqualTo(0);
    }

}
