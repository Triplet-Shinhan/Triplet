package com.ssafy.triplet.daily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.triplet.daily.domain.Daily;

@Repository
public interface DailyRepository extends JpaRepository<Daily, Long> {
	List<Daily> findAllByTripTripId(Long tripId);
}
