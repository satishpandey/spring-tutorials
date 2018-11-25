package com.cloudtechpro.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * REST API services sample application.
 * 
 * @author satish
 *
 */
@SpringBootApplication
public class RestApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
}
