package com.springboot.demo.async;

import java.time.LocalTime;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.config.AsyncConfig;

/**
 * Async처리 샘플
 * 
 * @see AsyncConfig
 * @author surely-kafka
 */
@RestController
@RequestMapping("/async")
public class AsyncDemoController {
	
	private static Logger log = LoggerFactory.getLogger(AsyncDemoController.class);
	
	@GetMapping("/callable/{message}")
	public Callable<String> asyncCallable(@PathVariable String message) {
		String previousNow = LocalTime.now().toString();
		return () ->{
			
			log.info("in another thread");
			
			Thread.sleep(2000);
			String result =  "[" + message + "] now was " + previousNow + " but now is " + LocalTime.now().toString();
			
			log.info("result : {}", result);
			return result;
		};
	}
}
