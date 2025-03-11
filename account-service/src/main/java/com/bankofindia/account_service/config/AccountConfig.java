package com.bankofindia.account_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
