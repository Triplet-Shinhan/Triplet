package com.ssafy.triplet.daily.service;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.daily.dto.DailyDto;
import com.ssafy.triplet.daily.dto.DashboardDto;
import com.ssafy.triplet.daily.dto.PaymentDto;
import com.ssafy.triplet.daily.repository.DailyRepository;
import com.ssafy.triplet.daily.util.DailyUtility;
import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.payment.service.PaymentService;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.trip.repository.TripRepository;
import com.ssafy.triplet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyService {

    private final TripRepository tripRepository;
    private final DailyRepository dailyRepository;
    private final DailyUtility dailyUtility;
    private final S3Service s3Service;
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


    public List<DailyDto> toDailyDtoList(User loginUser, Long tripId) {
        //프로젝트 ID를 찾을 수 없을 경우 에러
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new BaseException(ErrorCode.TRIP_ID_NOT_FOUND));
        Long userId = trip.getUser().getUserId();
        //접근 권한 확인
        if (!loginUser.getUserId().equals(userId)) {
            throw new BaseException(ErrorCode.NOT_AUTHORIZED);
        }

        paymentService.updatePaymentList(loginUser, tripId);
        List<Daily> dailies = trip.getDailies();
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
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
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
                    .date(payment.getDate())
                    .method(payment.getMethod())
                    .build();
            paymentDtoList.add(paymentDto);
        }
        return paymentDtoList;
    }

    public void saveImageUrl(Long dailyId, String url) {
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
        if (daily.getImageUrl() != null) {// 이미 업로드된 이미지가 있을 경우 s3에서 삭제
            s3Service.deleteImageFromS3(daily.getImageUrl());
        }
        daily.setImageUrl(url);
        dailyRepository.save(daily);
    }

    public Optional<String> deleteImageUrlFromDb(Long dailyId) {
        Daily daily = dailyRepository.findById(dailyId).orElseThrow(() -> new BaseException(ErrorCode.DAILY_ID_NOT_FOUND));
        String url = daily.getImageUrl();
        if(url == null) {
            throw new BaseException(ErrorCode.IMAGE_DELETE_ERROR);
        }
        daily.setImageUrl(null);
        dailyRepository.save(daily);
        return Optional.of(url);
    }
}
