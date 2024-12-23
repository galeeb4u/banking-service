package com.kafka.demo.producerserviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.demo.producerservice.MessageProducerService;
@Service
public class MessageProducerServiceIMP implements MessageProducerService{

	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	
	@Override
	public void sendMsgToTopic(String message) {
kafkaTemplate.send("codeDecode_Topic",message);
		
	}
}
