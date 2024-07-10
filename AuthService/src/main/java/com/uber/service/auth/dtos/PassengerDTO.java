package com.uber.service.auth.dtos;

import com.uber.service.entityservice.models.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;    // encrypted password
    private Date createdAt;

    public static PassengerDTO fromPassenger(Passenger passenger) {
        return PassengerDTO.builder()
                .id(passenger.getId())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .password(passenger.getPassword())
                .name(passenger.getName())
                .createdAt(passenger.getCreatedAt())
                .build();
    }
}
