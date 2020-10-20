package com.springboot.demo.thread;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackTutorial {
	
	private ExecutorService executorService;
	
	public CallbackTutorial() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
		
		@Override
		public void failed(Throwable exc, Void attachment) {
			System.out.println("failed() : " + exc.toString());
		}
		
		@Override
		public void completed(Integer result, Void attachment) {
			System.out.println("completed() : " + result);
			
		}
	};
	
	
	
	public void doWork(final String x, final String y) {
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				try {
					int intX   = Integer.parseInt(x);
					int intY   = Integer.parseInt(y);
					int result = intX + intY;
					callback.completed(result, null);
					Thread.sleep(3000);
					System.out.println("x : " + x + "\ty : " + y + " job finished.");
				}catch(NumberFormatException | InterruptedException e) {
					callback.failed(e, null);
				}
			}
		};
		
		executorService.submit(task);
	}
	
	
	public void finish() {
		executorService.shutdown();
	}
	
	
	
	public static void main(String[] args) {
		CallbackTutorial tutorial = new CallbackTutorial();
		tutorial.doWork("2", "2");
		tutorial.doWork("2", "TWO");
		tutorial.finish();
	}
	
}
