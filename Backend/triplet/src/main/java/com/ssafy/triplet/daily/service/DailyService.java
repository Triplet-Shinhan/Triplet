package com.ssafy.triplet.daily.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.triplet.payment.service.PaymentService;
import com.ssafy.triplet.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.DashboardDto;
import com.ssafy.triplet.daily.dto.PaymentDto;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.daily.util.DailyUtility;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyService {
	private final TripRepository tripRepository;
	private final DailyRepository dailyRepository;
	private final DailyUtility dailyUtility;
	private final PaymentService paymentService;

	public DashboardDto getDashboard(Long tripId) {
		Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
		DashboardDto dashboardDto = new DashboardDto();

		Long sumExpenditure = dailyUtility.getDailiesExpenditure(tripId);
		dashboardDto.setSumExpenditure(sumExpenditure);
		dashboardDto.setBudget(trip.getBudget() - sumExpenditure);
		dashboardDto.setCash(dailyUtility.getDailiesCashLeft(tripId));
		return dashboardDto;
	}

	public List<DailyDto> toDailyDtoList(User user,Long tripId) {
		paymentService.updatePaymentList(user,tripId);
		List<Daily> dailies = tripRepository.findById(tripId)
			.orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND))
			.getDailies();
		List<DailyDto> dtoList = new ArrayList<>();
		for (Daily daily : dailies) {
			DailyDto dailyDto = new DailyDto();
			dailyDto.setDailyId(daily.getDailyId());
			dailyDto.setDate(daily.getDate());
			dailyDto.setImageUrl(null);//S3 활용하기
			dailyDto.setSum(dailyUtility.getDailyExpenditure(daily));
			dtoList.add(dailyDto);
		}
		return dtoList;
	}

	public List<PaymentDto> getPayments(Long dailyId) {
		Daily daily = dailyRepository.findById(dailyId)
			.orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
		List<Payment> payments = daily.getPayments();
		if (payments == null) {
			throw new BaseException(ErrorCode.NULL_ERROR);
		}
		List<PaymentDto> paymentDtoList = new ArrayList<>();
		for (Payment payment : payments) {
			PaymentDto paymentDto = PaymentDto.builder()
				.paymentId(payment.getPaymentId())
				.item(payment.getItem())
				.cost(payment.getCost())
				.foreignCurrency(payment.getForeignCurrency())
				.date(payment.getDate())
				.method(payment.getMethod())
				.build();
			paymentDtoList.add(paymentDto);
		}
		return paymentDtoList;
	}
}
