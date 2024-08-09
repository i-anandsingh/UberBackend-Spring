package com.uber.service.bookingservice.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "sample-topic", groupId = "sample-groupId2")
    public void consume(String message) {
        System.out.println(message + " received on sample-topic inside booking service");
    }
}
