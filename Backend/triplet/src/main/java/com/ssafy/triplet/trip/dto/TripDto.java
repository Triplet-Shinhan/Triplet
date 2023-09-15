package com.ssafy.triplet.trip.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonProperty("prjName")
	private String prjName;

	@JsonProperty("location")
	private String location;

	@JsonProperty("budget")
	private Long budget;

	@JsonProperty("exchangedBudget")
	private Long exchangedBudget;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("startDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@JsonProperty("endDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
}
