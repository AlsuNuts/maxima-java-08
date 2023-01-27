package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void start() {
        System.out.println(repository.findAll());
    }
    public List<Product> getAllProducts(){
        return repository.findAll();
    }
    public void createProduct(Product product){
        repository.create(product);
    }
}
