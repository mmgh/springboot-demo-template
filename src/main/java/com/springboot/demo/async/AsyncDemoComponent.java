package com.springboot.demo.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class AsyncDemoComponent {

	private static Logger log = LoggerFactory.getLogger(AsyncDemoComponent.class);

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	Random random = new Random();

	public CompletableFuture<String> asyncCall(String id) {

		return CompletableFuture.supplyAsync(() -> {
			Integer time = random.nextInt(10);
			log.info("supplyAsync - id : {} / time : {}", id, time);
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("async return : {}", id);
			return id;
		}, threadPoolTaskExecutor);

	}

}
