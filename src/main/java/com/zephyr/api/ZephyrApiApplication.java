package com.zephyr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
public class ZephyrApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZephyrApiApplication.class, args);
	}

}

