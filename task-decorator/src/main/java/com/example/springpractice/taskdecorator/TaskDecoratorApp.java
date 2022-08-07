package com.example.springpractice.taskdecorator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TaskDecoratorApp {
    public static void main(String[] args) {
        SpringApplication.run(TaskDecoratorApp.class, args);
    }
}
