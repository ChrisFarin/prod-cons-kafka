package com.producer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class ProducerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProducerApplication.class, args);

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("client.id", "simple-producer-XX");

		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);

		String topic = "test";
		Producer<String, String> producer = new KafkaProducer<>( props );

		for( int i = 0; i < 10; i++ ){
			ProducerRecord<String, String> message = new ProducerRecord<>( topic, "this is messagez " + i );
			producer.send( message );
			System.out.println("message sent.");
		}

		producer.close(); // don't forget this
	}

}
