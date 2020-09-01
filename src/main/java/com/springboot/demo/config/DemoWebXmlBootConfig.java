package com.springboot.demo.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:demo-config/*.xml")
public class DemoWebXmlBootConfig {

	private static Logger logger = LoggerFactory.getLogger(DemoWebXmlBootConfig.class);
	
	@PostConstruct
	public void init() {
		logger.info("[fwk]DemoWebXmlBootConfig init....");
	}
	
}
