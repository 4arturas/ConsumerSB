package com.consumer;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ConsumerApp
{

	@GetMapping("/")
	public String index(final Banner.Mode m )
	{
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApp.class, args);
	}

}
