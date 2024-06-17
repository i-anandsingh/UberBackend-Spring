package com.example.reviewservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review_table")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    private Double rating;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)   // annotation tells spring about the format of the Date object to be stored i.e. Date/ Time/ Timestamp
    @CreatedDate    // annotation tells spring only handle at object creation
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate   // annotation tells spring only handle at object update
    private Date updatedAt;
}
