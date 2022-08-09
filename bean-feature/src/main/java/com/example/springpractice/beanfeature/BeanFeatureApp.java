package com.example.springpractice.beanfeature;

import com.example.springpractice.beanfeature.conditional.ConditionalBeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConditionalBeanConfig.class)
public class BeanFeatureApp {
    public static void main(String[] args) {
        SpringApplication.run(BeanFeatureApp.class, args);
    }
}
