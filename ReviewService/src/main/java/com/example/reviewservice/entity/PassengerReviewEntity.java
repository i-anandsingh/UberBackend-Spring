package com.example.reviewservice.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PassengerReviewEntity extends ReviewEntity{
    private String passengerReviewEntity;
}
