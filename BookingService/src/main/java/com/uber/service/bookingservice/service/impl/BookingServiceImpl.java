package com.uber.service.bookingservice.service.impl;

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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final PassengerRepository passengerRepository;
    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;
    private static final String LOCATION_SERVICE = "http://localhost:9093";

    public BookingServiceImpl(PassengerRepository passengerRepository, BookingRepository bookingRepository) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
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
        ResponseEntity<DriverLocationDTO[]> result = restTemplate.postForEntity(LOCATION_SERVICE + "/api/location/nearby/drivers", requestDTO, DriverLocationDTO[].class);

        if(result.getStatusCode().is2xxSuccessful() && Objects.nonNull(result.getBody())) {
            List<DriverLocationDTO> driverLocationDTOS = Arrays.asList(result.getBody());
            driverLocationDTOS.forEach(driverLocationDTO -> {
                System.out.println(driverLocationDTO.getDriverId() + " - " + "lat: " + driverLocationDTO.getLatitude() + " long: " + driverLocationDTO.getLongitude());
            });
        }

        return CreateBookingResponseDTO.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(String.valueOf(newBooking.getBookingStatus()))
//                .driver(Optional.of(newBooking.getDriver()))
                .build();
    }
}
