package org.anouar.anouarbillingservice;

import org.anouar.anouarbillingservice.dtos.InvoiceRequestDto;
import org.anouar.anouarbillingservice.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class AnouarBillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnouarBillingServiceApplication.class, args);
	}
    @Bean
	CommandLineRunner commandLineRunner(InvoiceService invoiceService){
      return args -> {
		  invoiceService.save(new InvoiceRequestDto(BigDecimal.valueOf(400000),"C01"));
		  invoiceService.save(new InvoiceRequestDto(BigDecimal.valueOf(60000),"C01"));
		  invoiceService.save(new InvoiceRequestDto(BigDecimal.valueOf(780000),"C02"));

	  };
	}

}
