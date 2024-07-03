package com.uber.service.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expirationTime;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * This method create a new JWT Token based on payload
     */
    private String createJWTToken(Map<String, Object> payload, String username) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime * 1000L);
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .subject(username)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", "abcd@gmail.com");
        payload.put("phoneNumber", "21323232323");
        String result = createJWTToken(payload, "anandsingh");
        System.out.println("Generated JWT token: " + result);
    }
}
