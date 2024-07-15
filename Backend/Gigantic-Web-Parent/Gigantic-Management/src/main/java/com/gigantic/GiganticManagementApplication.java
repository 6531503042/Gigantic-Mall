package com.gigantic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.gigantic")
@EnableScheduling
public class GiganticManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiganticManagementApplication.class, args);
	}

}
