package com.uber.service.bookingservice.service.impl;

import com.uber.service.bookingservice.apis.LocationServiceApi;
import com.uber.service.bookingservice.models.CreateBookingRequestDTO;
import com.uber.service.bookingservice.models.CreateBookingResponseDTO;
import com.uber.service.bookingservice.models.DriverLocationDTO;
import com.uber.service.bookingservice.models.NearbyDriversRequestDTO;
import com.uber.service.bookingservice.repository.BookingRepository;
import com.uber.service.bookingservice.repository.PassengerRepository;
import com.uber.service.bookingservice.service.BookingService;
import com.uber.service.entityservice.models.Booking;
import com.uber.service.entityservice.models.BookingStatus;
import com.uber.service.entityservice.models.Passenger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;
    private final LocationServiceApi locationServiceApi;
//    private static final String LOCATION_SERVICE = "http://localhost:9093";

    public BookingServiceImpl(PassengerRepository passengerRepository, BookingRepository bookingRepository, LocationServiceApi locationServiceApi) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
        this.locationServiceApi = locationServiceApi;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public CreateBookingResponseDTO createBooking(CreateBookingRequestDTO createBookingRequestDTO) {
        Optional<Passenger> passenger =  passengerRepository.findById(createBookingRequestDTO.getPassengerId());

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_RIDER)
                .startLocation(createBookingRequestDTO.getStartLocation())
                .endLocation(createBookingRequestDTO.getEndLocation())
                .passenger(passenger.get())
                .build();

        Booking newBooking = bookingRepository.save(booking);

        NearbyDriversRequestDTO requestDTO = NearbyDriversRequestDTO.builder()
                .latitude(createBookingRequestDTO.getStartLocation().getLatitude())
                .longitude(createBookingRequestDTO.getStartLocation().getLongitude())
                .build();

        processNearbyDriversAsync(requestDTO);
//        ResponseEntity<DriverLocationDTO[]> result = restTemplate.postForEntity(LOCATION_SERVICE + "/api/location/nearby/drivers", requestDTO, DriverLocationDTO[].class);
//
//        if(result.getStatusCode().is2xxSuccessful() && Objects.nonNull(result.getBody())) {
//            List<DriverLocationDTO> driverLocationDTOS = Arrays.asList(result.getBody());
//            driverLocationDTOS.forEach(driverLocationDTO -> {
//                System.out.println(driverLocationDTO.getDriverId() + " - " + "lat: " + driverLocationDTO.getLatitude() + " long: " + driverLocationDTO.getLongitude());
//            });
//        }

        return CreateBookingResponseDTO.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(String.valueOf(newBooking.getBookingStatus()))
                .build();
    }

    private void processNearbyDriversAsync(NearbyDriversRequestDTO nearbyDriversRequestDTO) {
        Call<DriverLocationDTO[]> call = locationServiceApi.getNearbyDrivers(nearbyDriversRequestDTO);
        call.enqueue(new Callback<DriverLocationDTO[]>() {
            @Override
            public void onResponse(Call<DriverLocationDTO[]> call, Response<DriverLocationDTO[]> response) {
                if(response.isSuccessful() && Objects.nonNull(response.body())) {
                    List<DriverLocationDTO> driverLocationDTOS = Arrays.asList(response.body());
                    driverLocationDTOS.forEach(driverLocationDTO -> {
                        System.out.println(driverLocationDTO.getDriverId() + " - " + "lat: " + driverLocationDTO.getLatitude() + " long: " + driverLocationDTO.getLongitude());
                    });
                } else{
                    System.out.println("Request Failed" + response.message());
                }
            }

            @Override
            public void onFailure(Call<DriverLocationDTO[]> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
