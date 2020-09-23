package com.springboot.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.demo.async.AsyncDemoComponent;

@SpringBootTest 
@RunWith(SpringRunner.class) 
@AutoConfigureMockMvc
class AsyncComponentTest {

	private static Logger log = LoggerFactory.getLogger(AsyncComponentTest.class);
	
	
    @Autowired
    private AsyncDemoComponent asyncComponent;


    @Test
    public void allOf_test(){

        Integer expectedPrice = 1100 + 1300 + 900;

        CompletableFuture<String> futureA = asyncComponent.asyncCall("latte");
        CompletableFuture<String> futureB = asyncComponent.asyncCall("mocha");
        CompletableFuture<String> futureC = asyncComponent.asyncCall("americano");

        List<CompletableFuture<String>> completableFutureList
                = Arrays.asList(futureA, futureB, futureC);

        
        List<String> resultPrice = CompletableFuture.allOf(futureA, futureB, futureC)
        		.thenApply(Void -> completableFutureList.stream().map(c -> c.join()).collect(Collectors.toList()))
        		.join();
        log.info("result : {}", resultPrice);
        

        //assertEquals(expectedPrice, resultPrice);

    }
}