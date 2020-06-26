package com.java.userproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProjectApplication.class, args);
	}

}
