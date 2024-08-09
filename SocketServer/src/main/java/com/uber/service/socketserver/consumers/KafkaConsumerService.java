package com.uber.service.socketserver.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "sample-topic")
    public void consume(String message) {
        System.out.println(message + " received on sample-topic");
    }
}
