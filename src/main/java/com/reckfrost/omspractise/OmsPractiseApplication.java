package com.reckfrost.omspractise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
//		(scanBasePackages={
//		"com.reckfrost.omspractise.mapper"})
public class OmsPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsPractiseApplication.class, args);
	}

}
