package com.uber.service.socketserver.controller;

import com.uber.service.socketserver.models.RideRequestDTO;
import com.uber.service.socketserver.models.RideResponseDTO;
import com.uber.service.socketserver.models.UpdateBookingRequestDTO;
import com.uber.service.socketserver.models.UpdateBookingResponseDTO;
import com.uber.service.socketserver.producers.KafkaProducerService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
@RequestMapping("/api/socket")
public class DriverRequestController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RestTemplate restTemplate;
    private final KafkaProducerService kafkaProducerService;

    public DriverRequestController(SimpMessagingTemplate messagingTemplate, KafkaProducerService kafkaProducerService) {
        this.messagingTemplate = messagingTemplate;
        this.restTemplate = new RestTemplate();
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping
    public Boolean help() {
        kafkaProducerService.publishMessage("sample-topic", "Message from Kafka Producer");
        return true;
    }

    @PostMapping("/newRide")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDTO rideRequestDTO) {
        System.out.println("Request for rides received.");
        sendDriversNewRiderRequests(rideRequestDTO);
        System.out.println("Request Completed");
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void sendDriversNewRiderRequests(RideRequestDTO rideRequestDTO) {
        messagingTemplate.convertAndSend("/topic/rideRequest", rideRequestDTO);
    }

    @MessageMapping("/rideResponse/{userId}")
    public synchronized void rideResponseHandler(@DestinationVariable String userId, RideResponseDTO rideResponseDTO){
        UpdateBookingRequestDTO requestDTO = UpdateBookingRequestDTO.builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .bookingStatus("SCHEDULED")
                .build();
        System.out.println("Response of ride request" + rideResponseDTO.getSuccess() + " " + userId);
        ResponseEntity<UpdateBookingResponseDTO> response = this.restTemplate.postForEntity("http://localhost:9095/api/v1/booking/"+rideResponseDTO.getBookingId(), requestDTO, UpdateBookingResponseDTO.class);
    }
}
