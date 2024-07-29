package com.example.Eray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Eray.model")
public class ErayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErayApplication.class, args);
	}
}
