package com.ssafy.triplet.trip.util;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.trip.dto.TripDto;
import com.ssafy.triplet.trip.dto.TripEditDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TripValidation {
	public void checkCreateValid(TripDto tripDto) {
		if (isInputInvalid(tripDto.getPrjName())) {
			throw new BaseException(ErrorCode.PRJ_NAME_ERROR);
		} else if (isInputInvalid(tripDto.getLocation())) {
			throw new BaseException(ErrorCode.LOCATION_ERROR);
		} else if (isInputInvalid(tripDto.getPrjName())) {
			throw new BaseException(ErrorCode.PRJ_NAME_ERROR);
		} else if (isInputInvalid(String.valueOf(tripDto.getBudget()))) {
			throw new BaseException(ErrorCode.BUDGET_ERROR);
		} else if (isInputInvalid(String.valueOf(tripDto.getExchangedBudget()))) {
			throw new BaseException(ErrorCode.EXCHANGED_BUDGET_ERROR);
		} else if (isInputInvalid(tripDto.getCurrency())) {
			throw new BaseException(ErrorCode.CURRENCY_ERROR);
		} else if (isInputInvalid(tripDto.getStartDate().toString())) {
			throw new BaseException(ErrorCode.START_DATE_ERROR);
		} else if (isInputInvalid(tripDto.getEndDate().toString())) {
			throw new BaseException(ErrorCode.END_DATE_ERROR);
		}
	}

	public void checkEditValid(TripEditDto tripEditDto) {
		if (isInputInvalid(tripEditDto.getStartDate().toString())) {
			throw new BaseException(ErrorCode.START_DATE_ERROR);
		} else if (isInputInvalid(tripEditDto.getEndDate().toString())) {
			throw new BaseException(ErrorCode.END_DATE_ERROR);
		}
	}

	private boolean isInputInvalid(String target) {
		return target == null || target.isEmpty() || target.contains(" ");
	}
}
