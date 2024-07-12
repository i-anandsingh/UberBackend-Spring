package com.uber.service.entityservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DBConstants extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private String value;
}
