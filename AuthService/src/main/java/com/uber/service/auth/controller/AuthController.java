package com.uber.service.auth.controller;

import com.uber.service.auth.dtos.PassengerSignUpRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {



    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signUp(@RequestBody PassengerSignUpRequestDTO requestDTO){
//        return new ResponseEntity<>();
        return null;
    }

//    @PostMapping("/signup/driver")
//    public ResponseEntity<?> signUp(){
//        return new ResponseEntity<>();
//    }

}
