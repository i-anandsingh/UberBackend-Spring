package com.uber.service.bookingservice.service;

import com.uber.service.bookingservice.models.CreateBookingRequestDTO;
import com.uber.service.bookingservice.models.CreateBookingResponseDTO;
import com.uber.service.bookingservice.models.UpdateBookingRequestDTO;
import com.uber.service.bookingservice.models.UpdateBookingResponseDTO;

public interface BookingService {
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO createBookingRequestDTO);
    public UpdateBookingResponseDTO updateBooking(UpdateBookingRequestDTO updateBookingRequestDTO, Long bookingId);
}
