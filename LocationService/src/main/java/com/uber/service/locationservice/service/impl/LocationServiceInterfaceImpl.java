package com.uber.service.locationservice.service.impl;

import com.uber.service.locationservice.models.DriverLocationDTO;
import com.uber.service.locationservice.service.LocationServiceInterface;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LocationServiceInterfaceImpl implements LocationServiceInterface {

    private static final String DRIVER_GEO_OPS_KEY = "drivers";
    private static final Double SEARCH_RADIUS_KEY = 5.0;

    private final StringRedisTemplate stringRedisTemplate;

    public LocationServiceInterfaceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Boolean saveLocation(String driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        geoOperations.add(
                DRIVER_GEO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(
                        driverId,
                        new Point(
                                latitude, longitude)
                ));
        return true;
    }

    @Override
    public List<DriverLocationDTO> getNearbyDrivers(Double latitude, Double longitude) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        Distance radius = new Distance(SEARCH_RADIUS_KEY, Metrics.KILOMETERS);
        Circle searchWithin = new Circle(
                new Point(
                        latitude, longitude),
                radius);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(DRIVER_GEO_OPS_KEY, searchWithin);
        List<DriverLocationDTO> nearbyDrivers = new ArrayList<>();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> result : results){
            Point point = Objects.requireNonNull(geoOperations.position(DRIVER_GEO_OPS_KEY, result.getContent().getName())).get(0);
            DriverLocationDTO driverLocationDTO = DriverLocationDTO.builder()
                    .driverId(result.getContent().getName())
                    .latitude(point.getX())
                    .longitude(point.getY())
                    .build();
            nearbyDrivers.add(driverLocationDTO);
        }
        return nearbyDrivers;
    }
}
