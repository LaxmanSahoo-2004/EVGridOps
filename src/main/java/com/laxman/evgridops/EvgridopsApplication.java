package com.laxman.evgridops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EvgridopsApplication {

	public static void main(String[] args) {

		SpringApplication.run(EvgridopsApplication.class, args);
	}

}
