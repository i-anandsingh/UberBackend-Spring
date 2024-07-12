package com.uber.service.entityservice.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTP extends BaseEntity {
    private String otpCode;
    private String sentToNumber;

    public static OTP makeOtp(String phoneNumber){
        Random randomNumber = new Random();
        int otpCode = randomNumber.nextInt(999999);
        return OTP.builder()
                .otpCode(Integer.toString(otpCode))
                .sentToNumber(phoneNumber)
                .build();
    }
}
