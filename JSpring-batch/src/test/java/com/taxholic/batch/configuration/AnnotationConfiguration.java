package com.taxholic.batch.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration

@ComponentScan(basePackages = {
		"com.taxholic.batch.configuration"
		,"com.taxholic.batch.dao"
		,"com.taxholic.batch.job"
		,"com.taxholic.batch.service"
	})


public class AnnotationConfiguration {
	
//	@Bean
//	public MessageSourceAccessor messageSourceAccessor() throws IOException {
//		return new MessageSourceAccessor(messageSource());
//	}
	
}