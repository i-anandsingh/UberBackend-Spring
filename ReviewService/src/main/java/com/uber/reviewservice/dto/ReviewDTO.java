package com.uber.reviewservice.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String content;
    private Double rating;
    private Long booking;
    private Date createdAt;
    private Date updatedAt;


}