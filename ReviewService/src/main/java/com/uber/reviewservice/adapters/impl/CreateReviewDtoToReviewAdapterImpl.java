package com.uber.reviewservice.adapters.impl;

import com.uber.reviewservice.adapters.CreateReviewDtoToReviewAdapter;
import com.uber.reviewservice.dto.CreateReviewDTO;
import com.uber.reviewservice.entity.Booking;
import com.uber.reviewservice.entity.Review;
import com.uber.reviewservice.repository.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateReviewDtoToReviewAdapterImpl implements CreateReviewDtoToReviewAdapter {
    private final BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertDTO(CreateReviewDTO createReviewDTO) {
        Optional<Booking> booking = bookingRepository.findById(createReviewDTO.getBookingId());
        return booking.map(value -> Review.builder()
                .rating(createReviewDTO.getRating())
                .booking(value)
                .content(createReviewDTO.getReview())
                .build()).orElse(null);
    }
}
