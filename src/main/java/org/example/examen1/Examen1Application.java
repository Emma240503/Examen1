package org.example.examen1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.example.examen1", "presentation", "data"})
@EnableJpaRepositories(basePackages = "data")

public class Examen1Application {
    public static void main(String[] args) {
        SpringApplication.run(Examen1Application.class, args);
    }
}