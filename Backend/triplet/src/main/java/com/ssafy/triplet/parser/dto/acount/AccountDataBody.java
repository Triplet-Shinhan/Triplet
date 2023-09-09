package com.ssafy.triplet.parser.dto.acount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class AccountDataBody {
    @JsonProperty("계좌번호")
    private String accountNum;

    @JsonProperty("상품명")
    private String productName;

    @JsonProperty("계좌잔액")
    private String accountBalance;

    @JsonProperty("고객명")
    private String name;

    @JsonProperty("거래내역반복횟수")
    private String transactionRepeatCount;

    @JsonProperty("거래내역")
    private List<Transaction> transactions;
}
