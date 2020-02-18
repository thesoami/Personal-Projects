package com.trilogyed.u2m2eurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class U2m2EurekaServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(U2m2EurekaServiceRegistryApplication.class, args);
	}

}
