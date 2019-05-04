package com.xxx.vertx.springbootvertxx.repository;

import com.xxx.vertx.springbootvertxx.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p from Product p where p.productId=?1")
    Product findByProductId(Integer productId);
}
