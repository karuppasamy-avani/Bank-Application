package com.bankofindia.transaction_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
