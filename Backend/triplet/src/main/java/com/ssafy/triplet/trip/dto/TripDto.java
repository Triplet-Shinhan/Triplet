package com.ssafy.triplet.trip.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {
	@JsonProperty("userId")
	private Long userId;

	@JsonProperty("prjName")
	private String prjName;

	@JsonProperty("location")
	private String location;

	@JsonProperty("budget")
	private Long budget;

	@JsonProperty("exchangedBudget")
	private Long exchangedBudget;

	@JsonProperty("usedBudget")
	private Long usedBudget;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("fixedRate")
	private Float fixedRate;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;
}
