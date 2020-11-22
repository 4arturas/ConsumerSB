package com.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.ui.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@SpringBootApplication
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

	@Value( value = "${kafka.topic.example-topic}")
	private String topicExample;

	@KafkaListener( topics = "example-topic" )
	public void listenWithHeaders( @Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition )
	{
		String s = topicExample;
		final String msg = String.format("Received Message: %s from partition: %s", message, partition );
		log.info(  msg );
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApp.class, args);
	}

}
