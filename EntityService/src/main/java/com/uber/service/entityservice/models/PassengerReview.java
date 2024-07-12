package com.uber.service.entityservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PassengerReview extends Review{

    @Column(nullable = false)
    private String passengerReview;

    @Column(nullable = false)
    private String passengerRating;
}
