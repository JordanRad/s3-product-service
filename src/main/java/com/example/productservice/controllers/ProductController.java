package com.example.productservice.controllers;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts() {

        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(products::add);

        return new ResponseEntity(products, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {

        Product product = repository.findById(id).orElse(new Product());

        return product.getId() > 0 ?
                new ResponseEntity(product, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        if (repository.findByName(product.getName()) == null) {
            Product newProduct =  new Product();
            newProduct.setName(product.getName());
            newProduct.setQuantity(product.getQuantity());
            newProduct.setPrice(product.getPrice());
            newProduct.setDescription(product.getDescription());
            newProduct.setDetailedDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusa");
            System.out.println(product.getCategory());
            Category c = null;
            if(product.getCategory().toString().equals("Book"))
                c=Category.Book;
            if(product.getCategory().toString().equals("Magazine"))
                c=Category.Magazine;
            if(product.getCategory().toString().equals("Video Games"))
                c=Category.VideoGames;
            newProduct.setCategory(c);
            repository.save(newProduct);
            return
                    new ResponseEntity<>(String.format("Product with name %s has been added.", product.getName()), HttpStatus.OK);
        } else {
            return
                    new ResponseEntity<>(String.format("Product with name %s already exists.", product.getName()), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {

        Product updatedProduct =
                repository.findById(product.getId()).orElseThrow();

        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());

        repository.save(updatedProduct);

        return
                new ResponseEntity(String.format("Product with id %d has been successfully updated", updatedProduct.getId()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        repository.deleteById(id);
        if(repository.findById(id)!=null) {
            return new ResponseEntity<>(String.format("Product with name %d has just been deleted.", id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product cannot be deleted.", HttpStatus.NOT_FOUND);
        }
    }

}
