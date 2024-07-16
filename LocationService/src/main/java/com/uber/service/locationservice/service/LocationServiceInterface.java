package com.uber.service.locationservice.service;

import com.uber.service.locationservice.models.DriverLocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationServiceInterface {
    Boolean saveLocation(String driverId, Double latitude, Double longitude);
    List<DriverLocationDTO> getNearbyDrivers(Double latitude, Double longitude);
}
