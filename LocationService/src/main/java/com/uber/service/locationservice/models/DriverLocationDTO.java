package com.uber.service.locationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLocationDTO {
    private String driverId;
    private Double latitude;
    private Double longitude;
}
