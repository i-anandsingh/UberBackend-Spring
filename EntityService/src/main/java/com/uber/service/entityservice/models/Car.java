package com.uber.service.entityservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String plateNumber;

    private String brand;
    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;

    @ManyToOne  // there can be many cars of one color
    private Color color;
}
