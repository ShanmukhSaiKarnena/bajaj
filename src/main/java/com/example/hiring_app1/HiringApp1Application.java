package com.example.hiring_app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class HiringApp1Application {

	private static final Logger logger = LoggerFactory.getLogger(HiringApp1Application.class);

	public static void main(String[] args) {
		logger.info("Starting Bajaj Finserv Health Hiring Application...");
		SpringApplication.run(HiringApp1Application.class, args);
	}

}
