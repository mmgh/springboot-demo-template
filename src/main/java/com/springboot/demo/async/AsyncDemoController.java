package com.springboot.demo.async;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.StopWatch;
import org.springframework.util.concurrent.ListenableFuture;
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

	@Autowired
	AsyncDemoService asyncDemoService;

	@GetMapping("/callable/{message}")
	public Callable<String> asyncCallable(@PathVariable String message) {
		String previousNow = LocalTime.now().toString();
		return () -> {

			log.info("in another thread");

			Thread.sleep(2000);
			String result = "[" + message + "] now was " + previousNow + " but now is " + LocalTime.now().toString();

			log.info("result : {}", result);
			return result;
		};
	}

	@GetMapping("/listenable")
	public ListenableFuture<String> asyncListenableFuture() {

		StopWatch sw = new StopWatch();
		sw.start();

		ListenableFuture<String> listenableFuture4 = asyncDemoService.asyncCall4();
		ListenableFuture<String> listenableFuture2 = asyncDemoService.asyncCall2();
		ListenableFuture<String> listenableFuture3 = asyncDemoService.asyncCall3();
		listenableFuture4.addCallback(s -> log.info("return value : {}", s), e -> log.info(e.getMessage()));
		listenableFuture2.addCallback(s -> log.info("return value : {}", s), e -> log.info(e.getMessage()));
		listenableFuture3.addCallback(s -> log.info("return value : {}", s), e -> log.info(e.getMessage()));

		sw.stop();
		return new AsyncResult<String>(String.valueOf(sw.getTotalTimeSeconds()));
	}

	@GetMapping("/completable")
	public CompletableFuture<String> asyncCompletableFuture() {

		StopWatch sw = new StopWatch();
		sw.start();

		CompletableFuture<String> cf1 = asyncDemoService.asyncCall4().completable();
		CompletableFuture<String> cf2 = asyncDemoService.asyncCall2().completable();
		CompletableFuture<String> cf3 = asyncDemoService.asyncCall3().completable();

		List<CompletableFuture<String>> completableFutures = Arrays.asList(cf1, cf2, cf3);

		String response;
		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[3]))
				.thenApplyAsync(
						result -> completableFutures.stream().map(future -> future.join()).collect(Collectors.toList()))
				.thenAcceptAsync(messages -> messages.forEach(message -> new String(message)));

		sw.stop();
		return new AsyncResult<String>(String.valueOf(sw.getTotalTimeSeconds())).completable();
	}

}
