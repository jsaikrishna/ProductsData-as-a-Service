package com.product;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(MainApplication.class, args);
        readData();

    }

    public static void readData() throws IOException {
        Products p = new Products();
        p.getData();
    }
}

/**
 * docker build -f Dockerfile -t docker-spring-productsrestapi .
 * docker run -p 8088:8080 -v ~/Desktop/productsRESTAPI/data:/data docker-spring-productsrestapi

 */