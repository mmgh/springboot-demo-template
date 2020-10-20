package com.springboot.demo.thread;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureFunctionalInterfaceEx {

	private static Logger log = LoggerFactory.getLogger(FutureFunctionalInterfaceEx.class);


	public class CallbackFutureTask extends FutureTask<String> {

		Function successCallback;
		Consumer exceptionCallback;

		public <T> CallbackFutureTask(Callable<String> callable, Function successCallback, Consumer exceptionCallback) {
			super(callable);
			// Null이 아니면 그대로 리턴, null이면 NPE발생
			try {
				this.successCallback   = Objects.requireNonNull(successCallback);
				this.exceptionCallback = Objects.requireNonNull(exceptionCallback);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void done() {
			try {
				successCallback.apply(get());
			} catch (InterruptedException e) {
				// Interrupt가 발생했음을 알려줌(인터럽트 시그널을 전달하는것이 중요함)
				Thread.currentThread().interrupt();
			} catch (ExecutionException e) {
				// 비동기 작업을 수행중 에러 발생
				exceptionCallback.accept(e.getCause());

			}
		}

	}

	@Test
	public void futureTaskTest() throws InterruptedException {

		ExecutorService es = Executors.newCachedThreadPool();

		log.info("init");

		CallbackFutureTask f = new CallbackFutureTask(() -> {
			TimeUnit.SECONDS.sleep(2);
			log.info("Async");
			// throw new RuntimeException("Async Error.");
			return "Hello";

		}, (result) -> result, (t) -> log.info(((Throwable) t).getMessage()));

		es.execute(f);
		log.info("doing....");
		TimeUnit.SECONDS.sleep(5);
		
		try {
			log.info("result : {}", f.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdown();
		log.info("exit");

	}

}
