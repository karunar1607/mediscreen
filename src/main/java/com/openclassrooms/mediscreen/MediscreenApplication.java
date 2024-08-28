package com.openclassrooms.mediscreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.respository.PatientRepository;

@SpringBootApplication
public class MediscreenApplication {
	private static final Logger log = LoggerFactory.getLogger(MediscreenApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(MediscreenApplication.class, args);
	}

    
	
}
