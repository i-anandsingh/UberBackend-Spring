package com.uber.service.bookingservice.service;

import com.uber.service.bookingservice.models.CreateBookingRequestDTO;
import com.uber.service.bookingservice.models.CreateBookingResponseDTO;

public interface BookingService {
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO createBookingRequestDTO);
}
