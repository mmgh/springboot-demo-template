package com.springboot.demo.async;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class AsyncDemoService {

	private static Logger log = LoggerFactory.getLogger(AsyncDemoService.class);
	
	@Async
	public ListenableFuture<String> asyncCall3() {
		log.info("asyncCall3 start : {}", LocalTime.now());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("asyncCall3 end : {}", LocalTime.now());
		return new AsyncResult<String>("3");
	}
	
	@Async
	public ListenableFuture<String> asyncCall4() {
		log.info("asyncCall4 start : {}", LocalTime.now());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("asyncCall4 end : {}", LocalTime.now());
		return new AsyncResult<String>("4");
	}
	
	@Async
	public ListenableFuture<String> asyncCall2() {
		log.info("asyncCall2 start : {}", LocalTime.now());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("asyncCall2 end : {}", LocalTime.now());
		return new AsyncResult<String>("2");
	}
}
