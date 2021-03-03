package com.example.demo.quartz;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.quartz")
@Configuration
public class QuartzConfig {

	private String updateDate;
	
	public QuartzConfig() {
		
	}
	
	@Bean
	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
