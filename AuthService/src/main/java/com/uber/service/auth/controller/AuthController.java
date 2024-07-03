package com.uber.service.auth.controller;

import com.uber.service.auth.dtos.PassengerDTO;
import com.uber.service.auth.dtos.PassengerSignUpRequestDTO;
import com.uber.service.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> signUp(@RequestBody PassengerSignUpRequestDTO requestDTO){
        PassengerDTO passengerDTO = authService.signUp(requestDTO);
        return new ResponseEntity<>(passengerDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/signup/driver")
//    public ResponseEntity<?> signUp(){
//        return new ResponseEntity<>();
//    }

}
