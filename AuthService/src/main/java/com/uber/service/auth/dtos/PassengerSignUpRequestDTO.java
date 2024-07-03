package com.uber.service.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerSignUpRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
