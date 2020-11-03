package com.example.demo;

import com.example.demo.controller.ProductServiceController;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class DemoApplication {

	@Autowired
	private ProductServiceController productServiceController;



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}



}
