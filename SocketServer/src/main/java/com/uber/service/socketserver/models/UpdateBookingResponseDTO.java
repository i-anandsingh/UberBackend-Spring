package com.uber.service.socketserver.models;

import com.uber.service.entityservice.models.BookingStatus;
import com.uber.service.entityservice.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingResponseDTO {
    private Long bookingId;
    private BookingStatus bookingStatus;
    private Optional<Driver> driver;
}
