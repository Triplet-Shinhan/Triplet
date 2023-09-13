package com.ssafy.triplet.daily.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.payment.domain.Payment;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyService {
	private final DailyRepository dailyRepository;

	public List<DailyDto> toDailyDtoList(Long tripId) {
		List<Daily> dailies = dailyRepository.findAllByTripTripId(tripId);
		//dailies가 null일 경우 유효성 검증할지 고민해보기
		List<DailyDto> dtoList = new ArrayList<>();
		for (Daily daily : dailies) {
			DailyDto dailyDto = new DailyDto();
			dailyDto.setDailyId(daily.getDailyId());
			dailyDto.setDate(daily.getDate());
			dailyDto.setImageUrl(null);//S3 활용하기

			Long sum = 0L;
			List<Payment> payments = daily.getPayments();
			if (payments != null) {
				for (Payment payment : payments) {
					sum += payment.getCost();
				}
			}
			dailyDto.setSum(sum);
		}
		return dtoList;
	}

	public List<Payment> getPayments(Long dailyId) {
		Daily daily = dailyRepository.findById(dailyId)
			.orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
		return daily.getPayments();
	}
}
