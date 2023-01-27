package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public String getAllClass(Model model){
        model.addAttribute("products", service.getAllProducts());
        return "products";
    }
    @PostMapping("/product")
    public String createProduct(Product product, Model model){
        model.addAttribute("products", service.getAllProducts());
        model.addAttribute("title", "Список товаров");
        model.addAttribute("header_name", "Имя кота");
        return "products";
    }

}
