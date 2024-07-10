package com.uber.service.auth.service;

import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expirationTime;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * This method create a new JWT Token based on payload
     */
    private String createJwtToken(Map<String, Object> payload, String email) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime * 1000L);

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .subject(email)
                .signWith(getSignKey())
                .compact();
    }

    public String creatToken(String email) {
        return createJwtToken(new HashMap<>(), email);
    }

    /**
     * This method extracts all claims from the token
     * @param token JWT Token
     * @return claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * This method extracts a very particular piece of info from the token. e.g. Expiration
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * This method check if the token expiry was before the current time stamp.
     * @param token JWT Token
     * @return  True if expired else false.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean isValidJwt(String token, String email) {
        final String userEmailFetched = getClaimFromToken(token, Claims::getSubject);
        return (userEmailFetched.equals(email)) && !isTokenExpired(token);
    }

    private Object extractPayload(String token, String payloadKey) {
        Claims claim = getAllClaimsFromToken(token);
        return claim.get(payloadKey);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", "abcd@gmail.com");
        payload.put("phoneNumber", "21323232323");
        String token = createJwtToken(payload, "anandsingh");
        System.out.println("Generated JWT token: " + token);
        System.out.println(extractPayload(token, "phoneNumber").toString());
    }
}
