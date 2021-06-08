package com.bridgelabz.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FundooNotesApplication {

	public static void main(String[] args) {
		log.debug("Running fundoo notes app.....");
		SpringApplication.run(FundooNotesApplication.class, args);
	}

}
