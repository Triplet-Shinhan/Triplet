package com.ssafy.triplet.daily.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
	private Long paymentId;

	private String item;

	private Long cost;

	private String foreignCurrency;

	private LocalDateTime date;

	private String method;
}
