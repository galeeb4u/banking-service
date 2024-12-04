package com.kafka.demo.kafkacontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.demo.producerservice.MessageProducerService;
@RestController
@RequestMapping("/v2")
public class MessageKafkaController {
	@Autowired
	private MessageProducerService producer;
	
	
	@GetMapping("/message")
	public void getMessageFromClient(@RequestParam("message") String message) {
		producer.sendMsgToTopic(message);
	}


}
