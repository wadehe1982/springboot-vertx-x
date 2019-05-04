package com.xxx.vertx.springbootvertxx.entity;

import com.xxx.vertx.springbootvertxx.service.ProductService.ProductDTO;
import lombok.Data;
import lombok.val;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    public ProductDTO toDto(){
        val productDTO = new ProductDTO();
        BeanUtils.copyProperties(this, productDTO);
        return productDTO;
    }
}
