package com.ssafy.triplet.parser.dto.acount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction {
    @JsonProperty("거래일자")
    private String transactionDate;

    @JsonProperty("거래시간")
    private String transactionTime;

    @JsonProperty("적요")
    private String transactionSummary;

    @JsonProperty("출금금액")
    private String withdrawalAmount;

    @JsonProperty("입금금액")
    private String depositAmount;

    @JsonProperty("내용")
    private String content;

    @JsonProperty("잔액")
    private String remainingBalance;

    @JsonProperty("입지구분")
    private String locationType;

    @JsonProperty("거래점명")
    private String transactionPointName;
}
