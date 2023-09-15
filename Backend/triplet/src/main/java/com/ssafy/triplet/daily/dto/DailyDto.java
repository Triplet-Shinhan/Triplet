package com.ssafy.triplet.daily.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyDto {
	@JsonProperty("dailyId")
	private Long dailyId;

	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonProperty("imageUrl")
	private String imageUrl;

	@JsonProperty("sum")
	private Long sum;
}
