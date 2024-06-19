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

    @OneToOne(cascade = CascadeType.PERSIST)
    private ReviewEntity review;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;
}

/*
Review and Booking are related to each other. So before saving booking in db we need to have review already present.
Otherwise, error will be thrown. CascadeType helps in achieving this, it helps us in not needing to remember
what needs to be saved first in db and what last.
 */
