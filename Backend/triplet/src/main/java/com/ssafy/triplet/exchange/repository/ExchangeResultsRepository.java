package com.ssafy.triplet.exchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.triplet.exchange.domain.ExchangeRecord;

public interface ExchangeResultsRepository extends JpaRepository<ExchangeRecord, Integer> {
    List<ExchangeRecord> findByNameAndPhoneNumAndBirth(String name, String phoneNum, String birth);
}
