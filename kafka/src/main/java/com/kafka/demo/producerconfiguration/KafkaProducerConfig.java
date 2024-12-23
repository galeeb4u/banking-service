package com.kafka.demo.producerconfiguration;

import org.springframework.context.annotation.Configuration;

@Configuration

public class KafkaProducerConfig {
	/*
	 * @Value("${kafka.bootstrap.servers}") private String bootstrapServers;
	 * 
	 * @Bean public KafkaTemplate<String, Order1> kafkaTemplate(){
	 * 
	 * return new KafkaTemplate<>(producerfactory()) ;
	 * 
	 * }
	 * 
	 * @Bean public ProducerFactory<String , Order1> producerfactory(){ Map<String,
	 * Object> config = new HashMap();
	 * config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	 * config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class);
	 * config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * JsonSerializer.class); return new DefaultKafkaProducerFactory<>(config) ;
	 * 
	 * }
	 * 
	 */



}
