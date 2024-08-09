package com.uber.service.bookingservice.apis;


import com.uber.service.bookingservice.models.RideRequestDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface  SocketApi {
    @POST("/api/socket/newRide")
    Call<Boolean> raiseRideRequest(@Body RideRequestDTO rideRequestDTO);
}
