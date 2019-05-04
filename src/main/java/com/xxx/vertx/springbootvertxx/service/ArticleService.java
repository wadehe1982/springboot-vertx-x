package com.xxx.vertx.springbootvertxx.service;

import com.xxx.vertx.springbootvertxx.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService {

    public List<Product> getAllArticle() {
        Product product = new Product();
        product.setDescription("aa");
        product.setProductId(9);
        product.setProductName("product-9");
        List<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }
}
