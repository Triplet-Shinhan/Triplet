package com.ssafy.triplet.daily.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.daily.dto.DailyDto;

import com.ssafy.triplet.daily.dto.DashboardDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailiesResponse {
	@JsonProperty("tripId")
	private Long tripId;

	@JsonProperty("dashboard")
	private DashboardDto dashboardDto;

	@JsonProperty("dailies")
	private List<DailyDto> dailies;
}
