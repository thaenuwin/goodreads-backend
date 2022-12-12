package com.goodreadsbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class ApiApplication {


	@Autowired
	protected static PasswordEncoder encoder;
	public static void main(String... args){
		SpringApplication.run(ApiApplication.class, args);
		encoder.encode("IL0veCrypt0!!!");
	}



}
