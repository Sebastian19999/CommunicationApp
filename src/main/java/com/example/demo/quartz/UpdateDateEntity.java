package com.example.demo.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class UpdateDateEntity {

	@Autowired
	private static Environment env;
	
	public static String updateDate=env.getProperty("application.quartz.updateDate");

	
	
	
	
	
	
	
	
	
}
