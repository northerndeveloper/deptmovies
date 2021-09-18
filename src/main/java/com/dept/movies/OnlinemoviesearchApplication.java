package com.dept.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OnlinemoviesearchApplication {

    public final Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {
        SpringApplication.run(OnlinemoviesearchApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
