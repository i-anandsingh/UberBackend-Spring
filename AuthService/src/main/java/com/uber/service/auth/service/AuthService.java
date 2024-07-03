package com.uber.service.auth.service;

import com.uber.service.auth.dtos.PassengerDTO;
import com.uber.service.auth.dtos.PassengerSignUpRequestDTO;
import com.uber.service.auth.entity.Passenger;
import com.uber.service.auth.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthService(
            PassengerRepository passengerRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDTO signUp(PassengerSignUpRequestDTO signUpRequestDTO) {
        Passenger passenger = Passenger.builder()
                .email(signUpRequestDTO.getEmail())
                .name(signUpRequestDTO.getName())
                .password(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()))
                .phoneNumber(signUpRequestDTO.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        return PassengerDTO.fromPassenger(newPassenger);
    }
}
