package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.models.User;
import com.example.productservice.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseEntity<?> getAllProducts(@PathVariable long id) {

        Product product = repository.findById(id).orElse(new Product());

        return product.getId() > 0 ?
                new ResponseEntity(product, HttpStatus.OK) :
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewProduct(@PathVariable Product product) {
        if (repository.findByName(product.getName()) == null) {
            repository.save(product);
            return
                    new ResponseEntity<>(String.format("Product with name %s has been added.", product.getName()), HttpStatus.OK);
        } else {
            return
                    new ResponseEntity<>(String.format("Product with name %s already exists.", product.getName()), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateProduct(@PathVariable Product product) {

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

    @DeleteMapping("/")
    public ResponseEntity<?> deleteProduct(@PathVariable Product product) {
        repository.delete(product);

        return
                new ResponseEntity<>(String.format("Product with name %s has just been deleted.", product.getName()), HttpStatus.OK);

    }

}
