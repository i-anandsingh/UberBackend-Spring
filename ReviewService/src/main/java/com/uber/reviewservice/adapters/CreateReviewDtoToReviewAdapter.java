package com.uber.reviewservice.adapters;

import com.uber.reviewservice.dto.CreateReviewDTO;
import com.uber.reviewservice.entity.Review;
import org.springframework.stereotype.Component;

@Component
public interface CreateReviewDtoToReviewAdapter {
    Review convertDTO(CreateReviewDTO createReviewDTO);
}
