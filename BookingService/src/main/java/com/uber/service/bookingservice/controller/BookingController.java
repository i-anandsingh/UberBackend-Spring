package com.uber.service.bookingservice.controller;

import com.uber.service.bookingservice.models.CreateBookingRequestDTO;
import com.uber.service.bookingservice.models.CreateBookingResponseDTO;
import com.uber.service.bookingservice.models.UpdateBookingRequestDTO;
import com.uber.service.bookingservice.models.UpdateBookingResponseDTO;
import com.uber.service.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDTO> createBooking(@RequestBody CreateBookingRequestDTO createBookingRequestDTO) {
        CreateBookingResponseDTO responseDTO = bookingService.createBooking(createBookingRequestDTO);
        return new ResponseEntity<>(new CreateBookingResponseDTO(), HttpStatus.CREATED);
    }

    @PostMapping("/{bookingId}")
    public ResponseEntity<UpdateBookingResponseDTO> updateBooking(
            @RequestBody UpdateBookingRequestDTO updateBookingRequestDTO,
            @PathVariable Long bookingId){
        return new ResponseEntity<>(bookingService.updateBooking(updateBookingRequestDTO, bookingId), HttpStatus.OK);
    }
}
