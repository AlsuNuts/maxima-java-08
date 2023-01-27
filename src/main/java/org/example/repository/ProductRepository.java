package org.example.repository;

import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired private RowMapper<Product> rowMapper;
    public void init(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS products");
        jdbcTemplate.execute("CREATE TABLE PRODUCTS(ID LONG, NAME VARCHAR(45))");
        jdbcTemplate.update("INSERT INTO products (id, name) VALUES (?, ?)", 1L, "Ибупрофен");
        jdbcTemplate.update("INSERT INTO products (id, name) VALUES (?, ?)", 2L, "Диклофенак");
        jdbcTemplate.update("INSERT INTO products (id, name) VALUES (?, ?)", 3L, "Зодак");

    }
    public List<Product> findAll(){
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM products", rowMapper));
    }
    public void create(Product product){
        jdbcTemplate.update("INSERT INTO products (id, name) VALUES (?, ?)", product.getId(), product.getName());
    }
}
