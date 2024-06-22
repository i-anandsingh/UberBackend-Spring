package com.uber.reviewservice.repository;

import com.uber.reviewservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByIdIn(List<Long> driverIds);
}
