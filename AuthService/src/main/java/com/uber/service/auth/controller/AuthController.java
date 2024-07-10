package com.uber.service.auth.controller;

import com.uber.service.auth.dtos.AuthRequestDTO;
import com.uber.service.auth.dtos.PassengerDTO;
import com.uber.service.auth.dtos.PassengerSignUpRequestDTO;
import com.uber.service.auth.service.AuthService;
import com.uber.service.auth.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${cookie.expiry}")
    private int cookieExpiry;

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDTO> signUpPassenger(@RequestBody PassengerSignUpRequestDTO requestDTO){
        PassengerDTO passengerDTO = authService.signUp(requestDTO);
        return new ResponseEntity<>(passengerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login/passenger")
    public ResponseEntity<?> loginPassenger(
            @RequestBody AuthRequestDTO authRequestDTO,
            HttpServletResponse response
    ){
        System.out.println("Request Received: " + authRequestDTO);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            String JwtToken = jwtService.creatToken(authRequestDTO.getEmail());
            ResponseCookie cookie = ResponseCookie
                    .from("JwtToken", JwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return new ResponseEntity<>("Successfully logged in", HttpStatus.OK);
        } else{
            throw new UsernameNotFoundException("Username or password is incorrect");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest httpServletRequest){
        for(Cookie cookie : httpServletRequest.getCookies()){
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }
        return new ResponseEntity<>("Token validated", HttpStatus.OK);
    }
}
