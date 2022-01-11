package org.openlab.openlabcustomerservice;

import org.openlab.openlabcustomerservice.dto.CustomerRequestDto;
import org.openlab.openlabcustomerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlabCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenlabCustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerService customerService){
		return args -> {
            customerService.save(new CustomerRequestDto("C01","adria","adria@gmail.com"));
            customerService.save(new CustomerRequestDto("C02","openlab","openlab@gmail.com"));
		};
	}

}
