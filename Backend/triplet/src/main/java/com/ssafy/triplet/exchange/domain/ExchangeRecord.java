package com.ssafy.triplet.exchange.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @JsonProperty("상태구분")
    private String state;

    // @JsonProperty("신청인명")
    private String name;

    // @JsonProperty("신청인전화번호")
    private String phoneNum;

    // @JsonProperty("신청인생년월일")
    private String birth;

    // @JsonProperty("신청일")
    private String applicationDate;

    // @JsonProperty("수령일")
    private String receiptDate;

    // @JsonProperty("수령점명")
    private String receiptLocation;

    // @JsonProperty("가상계좌입금일자")
    private String virtualAccountDepositDate;

    // @JsonProperty("가상입금계좌번호")
    private String virtualDepositAccount;

    // @JsonProperty("가상계좌입금금액")
    private String virtualAccountDepositAmount;

    // @JsonProperty("가상입금기한일자")
    private String deadlineDate;

    // @JsonProperty("가상입금기한시각")
    private String deadlineTime;

    // @JsonProperty("환전구분")
    private String exchangeType;

    // @JsonProperty("환전통화")
    private String currency;

    // @JsonProperty("환전금액")
    private String exchangeAmount;

    // @JsonProperty("우대적용환율")
    private String preferentialRate;

    // @JsonProperty("원화금액")
    private String KRWAmount;
}
