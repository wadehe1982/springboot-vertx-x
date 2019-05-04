package com.xxx.vertx.springbootvertxx.service;

import com.xxx.vertx.springbootvertxx.entity.Product;
import com.xxx.vertx.springbootvertxx.repository.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SuppressWarnings("unchecked")
    public List<ProductDTO> getAllProducts() {
        val entityList = productRepository.findAll();
        if (CollectionUtils.isEmpty(entityList)){
            return Collections.EMPTY_LIST;
        }
        return entityList.stream().map(Product::toDto).collect(Collectors.toList());
    }

    public ProductDTO findProductById(Integer productId){
        if (StringUtils.isEmpty(productId)){
            log.error("the input productId is null!");
            return null;
        }
        val entity = productRepository.findByProductId(productId);
        return Objects.isNull(entity) ? null : entity.toDto();
    }

    @Transactional
    public ProductDTO addProduct(ProductDTO toBeSaved){
        Product toBeSavedEntity = new Product();
        BeanUtils.copyProperties(toBeSaved, toBeSavedEntity);
        val savedEntity = productRepository.save(toBeSavedEntity);
        return savedEntity.toDto();
    }

    @Transactional
    public Integer deleteProduct(Integer productId){
        productRepository.deleteById(productId);
        log.info("product was deleted: {}", productId);
        return productId;
    }

    @Transactional
    public ProductDTO patchProduct(ProductDTO toBeSaved) {
        val entity = productRepository.findByProductId(toBeSaved.getProductId());
        entity.setProductName(toBeSaved.getProductName());
        entity.setDescription(toBeSaved.getDescription());
        val saved = productRepository.save(entity);
        return saved.toDto();
    }

    @Data
    public static class ProductDTO {
        private Integer productId;
        private String description;
        private String productName;
    }
}
