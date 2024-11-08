package com.codejourney.pimber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PimberApplication {

	public static void main(String[] args) {
		SpringApplication.run(PimberApplication.class, args);
	}

}
