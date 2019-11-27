package com.jadlokin.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ToolUIStartApplication {

	public static void main(String[] args){
//		SpringApplication.run(ToolUIStartApplication.class, args);
		new SpringApplicationBuilder(ToolUIStartApplication.class)
				.headless(false)
				.run(args);
	}

}
