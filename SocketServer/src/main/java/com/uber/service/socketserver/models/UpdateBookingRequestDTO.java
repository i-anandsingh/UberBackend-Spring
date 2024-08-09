package com.uber.service.socketserver.models;

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
