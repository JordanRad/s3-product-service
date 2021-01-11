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
        productOne.setDetailedDescription("Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        productOne.setCategory(Category.Book);
        productOne.setPrice(14.99);
        productOne.setQuantity(10);

        repository.save(productOne);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productOne.getName(),productOne.getCategory()));

        Product productTwo = new Product();
        productTwo.setName("Wild Game");
        productTwo.setDescription("by Brod");
        productTwo.setDetailedDescription("Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        productTwo.setCategory(Category.Book);
        productTwo.setPrice(10.99);
        productTwo.setQuantity(1);

        repository.save(productTwo);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productTwo.getName(),productTwo.getCategory()));

        Product productThree = new Product();
        productThree.setName("Bravo");
        productThree.setDescription("");
        productThree.setDetailedDescription("Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        productThree.setCategory(Category.Magazine);
        productThree.setPrice(19.99);
        productThree.setQuantity(111);

        repository.save(productThree);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productThree.getName(),productThree.getCategory()));

        Product productFour = new Product();
        productFour.setName("World of Warcraft I");
        productFour.setDescription("Vanilla");
        productFour.setDetailedDescription("Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        productFour.setCategory(Category.VideoGames);
        productFour.setPrice(59.99);
        productFour.setQuantity(0);

        repository.save(productFour);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productFour.getName(),productFour.getCategory()));

        Product productFive = new Product();
        productFive.setName("World of Warcraft II");
        productFive.setDescription("The Burning Crusade");
        productFive.setDetailedDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        productFive.setCategory(Category.VideoGames);
        productFive.setPrice(79.99);
        productFive.setQuantity(10);

        repository.save(productFive);
        System.out.println(String.format("Product with name: %1$s was added to %2$s category",productFive.getName(),productFive.getCategory()));

    }
}
