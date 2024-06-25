package com.uber.reviewservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
