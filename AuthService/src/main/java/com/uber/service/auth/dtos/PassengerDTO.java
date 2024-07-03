package com.uber.service.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;    // encrypted password
    private String createdAt;
}
