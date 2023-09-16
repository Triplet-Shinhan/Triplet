package com.ssafy.triplet.trip.repository;

import com.ssafy.triplet.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByUserUserId(Long userId);
}
