package com.learn.sbl.app;

import org.iartisan.runtime.env.EnvPropertiesLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@SpringBootApplication(scanBasePackages= {"com.learn.sbl","org.iartisan.runtime.web.config"})
public class SpringBootSampleApplication {

	public static void main(String[] args) {
		Properties properties = EnvPropertiesLoader.loadFile();
		SpringApplication application = new SpringApplication(SpringBootSampleApplication.class);
		application.setDefaultProperties(properties);
		application.run(args);
		//SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
