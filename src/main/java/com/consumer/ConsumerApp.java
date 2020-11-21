package com.consumer;

import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ConsumerApp
{

	@GetMapping("/")
	public String index(final Model m )
	{
		m.addAttribute("name", "Consumer");
		m.addAttribute("title", "Title");
		m.addAttribute("msg", "Msg2");
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApp.class, args);
	}

}
