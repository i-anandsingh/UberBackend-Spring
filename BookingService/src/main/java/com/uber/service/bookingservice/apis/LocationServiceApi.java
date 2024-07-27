package com.uber.service.bookingservice.apis;

import com.uber.service.bookingservice.models.DriverLocationDTO;
import com.uber.service.bookingservice.models.NearbyDriversRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import retrofit2.Call;
import retrofit2.http.Body;

public interface LocationServiceApi {
    @PostMapping("/api/location/nearby/drivers")
    Call<DriverLocationDTO[]> getNearbyDrivers(@Body NearbyDriversRequestDTO requestDTO);
}
