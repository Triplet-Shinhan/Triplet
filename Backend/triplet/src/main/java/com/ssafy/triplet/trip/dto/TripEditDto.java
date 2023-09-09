package com.ssafy.triplet.trip.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripEditDto {
	@JsonProperty("fixedRate")
	private Float fixedRate;

	@JsonProperty("startDate")
	private Date startDate;

	@JsonProperty("endDate")
	private Date endDate;
}
