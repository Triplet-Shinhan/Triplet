package com.ssafy.triplet.daily.response;

import java.util.List;

import com.ssafy.triplet.daily.dto.DailyDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailiesResponse {
	private Long tripId;
	private List<DailyDto> dailies;
}
