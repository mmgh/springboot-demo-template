package com.springboot.demo.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * if @Configuration beans are located different package, use @Import annotation.
 * @author surely-kafka
 *
 */
@Configuration
//@Import({
//	  DemoAppConfig.class
//	, DemoWebConfig.class
//})
public class DemoRootConfig {

	private static Logger logger = LoggerFactory.getLogger(DemoRootConfig.class);

	@PostConstruct
	public void init() {
		logger.info("[fwk]DemoRootConfig init....");
	}
	
}
