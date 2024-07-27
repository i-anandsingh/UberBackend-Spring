package com.uber.service.bookingservice.models;

import com.uber.service.entityservice.models.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingRequestDTO {
    private String bookingStatus;
    private Optional<Long> driverId;
}
