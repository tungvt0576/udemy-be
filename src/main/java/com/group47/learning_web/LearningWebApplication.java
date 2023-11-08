package com.group47.learning_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
public class LearningWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningWebApplication.class, args);
	}

}
