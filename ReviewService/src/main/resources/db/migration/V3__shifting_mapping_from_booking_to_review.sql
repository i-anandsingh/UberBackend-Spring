ALTER TABLE booking
    DROP FOREIGN KEY FK_BOOKING_ON_REVIEW;

ALTER TABLE booking_review
    ADD review_id BIGINT NULL;

ALTER TABLE booking_review
    ADD CONSTRAINT FK_BOOKING_REVIEW_ON_REVIEW FOREIGN KEY (review_id) REFERENCES booking_review (id);

ALTER TABLE booking
    DROP COLUMN review_id;