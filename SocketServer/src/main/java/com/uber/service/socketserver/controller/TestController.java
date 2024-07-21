package com.uber.service.socketserver.controller;

import com.uber.service.socketserver.models.TestRequestDTO;
import com.uber.service.socketserver.models.TestResponseDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate messagingTemplate;

    public TestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/ping")    // client will send request to /app/ping
    @SendTo("/topic/ping")  //client will listen to this uri on message broker
    public TestResponseDTO pingCheck(TestRequestDTO testRequestDTO) {
        System.out.println("Received Ping request: " + testRequestDTO.getData());
        return TestResponseDTO.builder().data("Received").build();
    }

    @Scheduled(fixedDelay = 2000)
    public void sendPeriodicMessage() {
        messagingTemplate.convertAndSend("/topic/schedule", "Periodic message sent" + System.currentTimeMillis());
    }
}
