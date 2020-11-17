package com.example.productservice.FakeData;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineStartupRunner implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Initializing data.....");

        Product productOne = new Product();
        productOne.setName("The Body");
        productOne.setDescription("by Bill");
        productOne.setCategory(Category.Book);
        productOne.setPrice(14.99);
        productOne.setQuantity(10);

        repository.save(productOne);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productOne.getName(),productOne.getCategory()));

        Product productTwo = new Product();
        productTwo.setName("Wild Game");
        productTwo.setDescription("by Brod");
        productTwo.setCategory(Category.Book);
        productTwo.setPrice(10.99);
        productTwo.setQuantity(1);

        repository.save(productTwo);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productTwo.getName(),productTwo.getCategory()));

        Product productThree = new Product();
        productThree.setName("Bravo");
        productThree.setDescription("");
        productThree.setCategory(Category.Magazine);
        productThree.setPrice(19.99);
        productThree.setQuantity(111);

        repository.save(productThree);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productThree.getName(),productThree.getCategory()));

        Product productFour = new Product();
        productFour.setName("World of Warcraft I");
        productFour.setDescription("Vanilla");
        productFour.setCategory(Category.VideoGames);
        productFour.setPrice(59.99);
        productFour.setQuantity(0);

        repository.save(productFour);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productFour.getName(),productFour.getCategory()));

        Product productFive = new Product();
        productFive.setName("World of Warcraft II");
        productFive.setDescription("The Burning Crusade");
        productFive.setCategory(Category.VideoGames);
        productFive.setPrice(79.99);
        productFive.setQuantity(10);

        repository.save(productFive);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productFive.getName(),productFive.getCategory()));

    }
}
