package com.ssafy.triplet.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OneTransferRequestDto {
	@JsonProperty("name")
	private String name;

	@JsonProperty("accountNum")
	private String accountNum;
}
