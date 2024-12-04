package com.kafka.demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class ConsumerService {
	@KafkaListener(topics = "codeDecode_Topic",groupId = "codeDecode_group")
	public void listenToTopic(String receivedMessage) {
		
		System.out.println("The Message received is "+receivedMessage);
		
	}

}
