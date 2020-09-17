package com.springboot.demo.thread;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.SuccessCallback;

public class FutureEx {

	private static Logger log = LoggerFactory.getLogger(FutureEx.class);

//	@Test
//	public void simpleFutureTest() throws InterruptedException, ExecutionException {
//		
//		ExecutorService es = Executors.newCachedThreadPool();
//		
//		log.info("init");
//		
//		Future<String> f = es.submit(()->{
//			TimeUnit.SECONDS.sleep(2);
//			log.info("Async");
//			return "Hello";
//		});
//		
//		log.info("doing....");
//		log.info(f.get());
//		log.info("exit");
//	}

	
	
//	@Test
//	public void futureTaskTest() throws InterruptedException {
//		ExecutorService es = Executors.newCachedThreadPool();
//		
//		log.info("init");
//
//		FutureTask<String> f = new FutureTask<String>(()-> {
//			TimeUnit.SECONDS.sleep(2);
//			log.info("Async");
//			return "Hello";
//		}) {
//			
//			//비동기 작업이 완료가 되면 호출됨
//			@Override
//			protected void done() {
//				try {
//					log.info(get());
//				} catch (InterruptedException | ExecutionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		};
//		
// 		es.execute(f);
// 		log.info("doing....");
// 		TimeUnit.SECONDS.sleep(5);
//		es.shutdown();
//		log.info("exit");
//		
//	}

	interface SuccessCallback {
		void onSucces(String result);
	}
	
	interface ExceptionCallback{
		void onError(Throwable t);
	}

	public class CallbackFutureTask extends FutureTask<String> {

		SuccessCallback sc;
		ExceptionCallback ec;

		public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ExceptionCallback ec) {
			super(callable);
			// Null이 아니면 그대로 리턴, null이면 NPE발생
			this.sc = Objects.requireNonNull(sc);
			this.ec = Objects.requireNonNull(ec);
		}
		
		@Override
		protected void done() {
			try {
				sc.onSucces(get());
			} catch (InterruptedException e) {
				//Interrupt가 발생했음을 알려줌(인터럽트 시그널을 전달하는것이 중요함) 
				Thread.currentThread().interrupt();
			} catch( ExecutionException e) {
				//비동기 작업을 수행중 에러 발생
				ec.onError(e.getCause());
				
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
			//throw new RuntimeException("Async Error.");
			return "Hello";

		}, new SuccessCallback() {
			@Override
			public void onSucces(String result) {
				log.info(result);
			}

		}, new ExceptionCallback() {
			@Override
			public void onError(Throwable t) {
				log.info(t.getMessage());
			}
		});

		es.execute(f);
		log.info("doing....");
		TimeUnit.SECONDS.sleep(5);
		es.shutdown();
		log.info("exit");

	}

}
