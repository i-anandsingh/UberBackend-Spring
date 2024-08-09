package com.uber.service.socketserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideResponseDTO {
    private Boolean success;
    private Long bookingId;
}
