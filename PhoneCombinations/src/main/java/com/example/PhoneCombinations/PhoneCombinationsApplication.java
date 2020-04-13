package com.example.PhoneCombinations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.PhoneCombinations")
public class PhoneCombinationsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PhoneCombinationsApplication.class, args);
	}

}
