package com.isignthis.consumer;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ConsumerApp
{
	public static void main(String[] args)
	{
		SpringApplication.run(ConsumerApp.class, args);
	}

}
