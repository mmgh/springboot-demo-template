package com.springboot.demo.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


/**
 * WebApplicationContext beans 
 * @author surely-kafka
 *
 */
@Configuration
public class DemoWebConfig {

	private static Logger logger = LoggerFactory.getLogger(DemoWebConfig.class);
	
	@PostConstruct
	public void init() {
		logger.info("[fwk]DemoWebConfig init....");
	}
	
}
