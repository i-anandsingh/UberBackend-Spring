package com.uber.reviewservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PassengerReviewEntity extends ReviewEntity{

    @Column(nullable = false)
    private String passengerReview;

    @Column(nullable = false)
    private String passengerRating;
}
