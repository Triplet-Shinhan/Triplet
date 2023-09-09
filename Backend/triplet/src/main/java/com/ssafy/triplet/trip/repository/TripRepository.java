package com.ssafy.triplet.trip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.triplet.trip.domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	Optional<Trip> findByUser_Id(Long userId);
}
