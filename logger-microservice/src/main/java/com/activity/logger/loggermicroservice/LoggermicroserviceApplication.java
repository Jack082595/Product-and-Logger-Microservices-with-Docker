package com.activity.logger.loggermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoggermicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggermicroserviceApplication.class, args);
	}

}
