package org.anouar.adriaeurikaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AdriaEurikaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdriaEurikaServiceApplication.class, args);
	}

}
