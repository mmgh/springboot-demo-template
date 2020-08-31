package com.springboot.demo.tutorial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.props.DemoProperties;
import com.springboot.demo.tutorial.dto.TutorialDto;

@RestController
public class TutorialRestController {

	private static Logger logger = LoggerFactory.getLogger(TutorialRestController.class);
	
	@Value("${server.port}")
	String serverPort;
	
	@Autowired
	DemoProperties demoProperties;
	
	
	@GetMapping("/test")
	public TutorialDto getTest() {
		logger.info("/test");
		TutorialDto tDto = new TutorialDto();
		tDto.setName("octo-cat");
		tDto.setPassword("password");
		tDto.setId("octo-cat");
		tDto.setEmail("not.admin@github.com");
		return tDto;
	}
	
	
	@GetMapping("/port")
	public String getPort() {
		return this.serverPort;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/demo")
	public DemoProperties getDemoProperties() {
		return this.demoProperties;
	}
}
