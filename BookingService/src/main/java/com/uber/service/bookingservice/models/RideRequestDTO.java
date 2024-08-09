package com.uber.service.bookingservice.models;

import com.uber.service.entityservice.models.ExactLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
    private Long passengerId;
    private List<Long> driverIds;
    private Long bookingId;
}
