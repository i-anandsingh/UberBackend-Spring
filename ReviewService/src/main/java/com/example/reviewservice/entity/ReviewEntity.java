package com.example.reviewservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookingReview")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ReviewEntity extends BaseEntity {

    @Column(nullable = false)
    private String content;

    private Double rating;
}
