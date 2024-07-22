package com.uber.service.locationservice.controller;

import com.uber.service.locationservice.models.DriverLocationDTO;
import com.uber.service.locationservice.models.NearbyDriversRequestDTO;
import com.uber.service.locationservice.models.SaveDriverLocationRequestDTO;
import com.uber.service.locationservice.service.LocationServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/location")
public class LocationController {

    private final LocationServiceInterface locationService;

    public LocationController(LocationServiceInterface locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(
            @RequestBody SaveDriverLocationRequestDTO requestDTO
    ) {
        try {
            Boolean response = locationService.saveLocation(requestDTO.getDriverId(), requestDTO.getLatitude(), requestDTO.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDTO>> getNearbyDrivers(
            @RequestBody NearbyDriversRequestDTO requestDTO
    ) {
        try{
            List<DriverLocationDTO> driverLocationDTOS = locationService.getNearbyDrivers(requestDTO.getLatitude(), requestDTO.getLongitude());
            return new ResponseEntity<>(driverLocationDTOS, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
