package com.company.taskereurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TaskerEurekaServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerEurekaServiceRegistryApplication.class, args);
	}

}
