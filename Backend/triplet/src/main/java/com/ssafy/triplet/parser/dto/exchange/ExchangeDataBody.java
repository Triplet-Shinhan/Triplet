package com.ssafy.triplet.parser.dto.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeDataBody {
    @JsonProperty("원화환산금액")
    private String convertedKRWAmount;

    @JsonProperty("적용환율")
    private String exchangeRate;

    @JsonProperty("우대율")
    private String preferentialRate;

    @JsonProperty("가상입금계좌번호")
    private String virtualAccountNumber;

    @JsonProperty("가상계좌입금금액")
    private String virtualAccountDepositAmount;

    @JsonProperty("가상입금기한일자")
    private String virtualDepositDeadlineDate;

    @JsonProperty("가상입금기한시각")
    private String virtualDepositDeadlineTime;
}
