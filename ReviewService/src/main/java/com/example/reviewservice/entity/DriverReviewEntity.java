package com.example.reviewservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "driver_review_id")
public class DriverReviewEntity extends ReviewEntity {
    private String driverReviewComment;
}
