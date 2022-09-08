package com.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Locale;

@SpringBootApplication
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "us"));
		SpringApplication.run(Application.class, args);
	}
}
