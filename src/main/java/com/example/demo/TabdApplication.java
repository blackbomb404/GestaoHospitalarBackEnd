package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabdApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabdApplication.class, args);
		System.out.println("Hello JDBC");
	}

}
