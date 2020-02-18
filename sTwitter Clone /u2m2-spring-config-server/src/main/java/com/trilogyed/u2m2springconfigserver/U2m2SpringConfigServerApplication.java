package com.trilogyed.u2m2springconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class U2m2SpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(U2m2SpringConfigServerApplication.class, args);
	}

}
