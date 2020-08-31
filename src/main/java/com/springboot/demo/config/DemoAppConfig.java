package com.springboot.demo.config;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
		basePackages = { "com.springboot.demo.tutorial" },
		includeFilters = @ComponentScan.Filter({ Component.class, Service.class, Repository.class }),
		excludeFilters = @ComponentScan.Filter({ Controller.class })
)
public class DemoAppConfig {

	private static Logger logger = LoggerFactory.getLogger(DemoAppConfig.class);
	
	@PostConstruct
	public void init() {
		logger.info("[fwk]DemoAppConfig init....");
	}
	
}
