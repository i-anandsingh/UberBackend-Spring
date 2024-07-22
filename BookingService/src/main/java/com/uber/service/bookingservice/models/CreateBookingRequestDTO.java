package com.uber.service.bookingservice.models;

import com.uber.service.entityservice.models.ExactLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequestDTO {
    private Long passengerId;
    private ExactLocation startLocation;
    private ExactLocation endLocation;
}
