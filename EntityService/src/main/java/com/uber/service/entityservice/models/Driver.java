package com.uber.service.entityservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Driver extends BaseEntity{
    private String name;

    @Column(unique = true, nullable = false)
    private String licenseNumber;

    private String phoneNumber;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Booking> bookings = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DriveApprovalStatus driveApprovalStatus;

    @OneToOne
    private ExactLocation exactLocation;

    private String activeCity;

    @DecimalMin(value = "0.00", message = "Rating must be greater than or equal to 0.00")
    @DecimalMax(value = "5.00", message = "Rating must be less than or equal to 5.00")
    private double rating;
}
