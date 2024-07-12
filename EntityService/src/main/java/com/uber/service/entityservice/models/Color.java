package com.uber.service.entityservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Color extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String color;
}
