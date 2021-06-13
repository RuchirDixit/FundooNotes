package com.bridgelabz.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FundooNotesApplication {


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		log.debug("Running fundoo notes app.....");
		SpringApplication.run(FundooNotesApplication.class, args);
	}

}
