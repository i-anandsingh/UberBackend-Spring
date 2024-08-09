package com.uber.service.socketserver.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExactLocation {
    private Double latitude;
    private Double longitude;
}
