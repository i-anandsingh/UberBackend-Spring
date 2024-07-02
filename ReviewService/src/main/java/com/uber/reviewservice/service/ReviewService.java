package com.uber.reviewservice.service;

import com.uber.reviewservice.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {

    Optional<Review> findReviewById(Long id);

    List<Review> findAllReviews();

    boolean deleteReviewById(Long id);

    Review publishReview(Review review);

    Review updateReview(Long id, Review review);

}