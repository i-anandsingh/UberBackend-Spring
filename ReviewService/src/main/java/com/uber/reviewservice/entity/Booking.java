package com.uber.reviewservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseEntity {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ReviewEntity review;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;
}
