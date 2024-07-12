package com.uber.service.entityservice.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExactLocation extends BaseEntity {
    private Double latitude;
    private Double longitude;
}
