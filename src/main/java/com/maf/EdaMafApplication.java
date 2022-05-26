package com.maf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.maf.connection.Connection;

@SpringBootApplication
@EnableAutoConfiguration
public class EdaMafApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdaMafApplication.class, args);
        new Connection();
    }
    
}
