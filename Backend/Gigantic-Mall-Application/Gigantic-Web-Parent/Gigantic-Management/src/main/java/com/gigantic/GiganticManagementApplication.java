package com.gigantic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.gigantic"})
@EntityScan({"com.gigantic.entity" , "com.gigantic.admin.user", "com.gigantic.admin.Controller", "com.gigantic.admin.Service", "com.gigantic.admin.Repository"})
public class GiganticManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiganticManagementApplication.class, args);
	}
}
