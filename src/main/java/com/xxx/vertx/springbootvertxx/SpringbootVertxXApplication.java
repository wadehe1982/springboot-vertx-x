package com.xxx.vertx.springbootvertxx;

import com.xxx.vertx.springbootvertxx.verticle.*;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * https://github.com/vert-x3/vertx-examples/blob/master/web-examples/src/main/java/io/vertx/example/web/rest/SimpleREST.java
 */

@Slf4j
@EnableJpaRepositories(basePackages = "com.xxx.vertx.springbootvertxx.repository")
@SpringBootApplication
public class SpringbootVertxXApplication {

    @Autowired
    private ProductServerVerticle productServerVerticle;

    @Autowired
    private JpaProductVerticle jpaProductVerticle;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVertxXApplication.class, args);

    }

    @PostConstruct
    public void deployVerticle() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(productServerVerticle);
        vertx.deployVerticle(jpaProductVerticle);
        vertx.exceptionHandler(throwable -> {
            log.error("exception happened: {}", throwable.toString());
        });
        log.info("verticle deployed!!");
    }

}
