package com.uber.service.bookingservice.models;

import com.uber.service.entityservice.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookingResponseDTO {
    private Long bookingId;
    private String bookingStatus;
    private Optional<Driver> driver;
}
