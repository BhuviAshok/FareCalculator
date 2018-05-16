package com.klm.travelapi;

import java.util.concurrent.Executor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@EnableAsync
@SpringBootApplication
public class FareCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FareCalculatorApplication.class, args);
	}
	
	
	
	 public Executor getAsyncExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(2);
	        executor.setMaxPoolSize(2);
	        executor.setQueueCapacity(500);
	        executor.setThreadNamePrefix("async-task-");
	        executor.initialize();
	        return executor;
	    }
}
