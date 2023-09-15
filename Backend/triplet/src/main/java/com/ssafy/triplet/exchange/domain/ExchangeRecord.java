package com.ssafy.triplet.exchange.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity(name = "ExchangeResults")
public class ExchangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonProperty("상태구분")
    @Column(nullable = false, length = 30)
    private String state;

    // @JsonProperty( " 신청인명")
    @Column(nullable = false, length = 30)
    private String name;

    // @JsonProperty( " 신청인전화번호")
    @Column(nullable = false, length = 30)
    private String phoneNum;

    // @JsonProperty("신청인생년월일")
    @Column(nullable = false, length = 10)
    private String birth;

    // @JsonProperty("신청일")
    @Column(nullable = false, length = 10)
    private String applicationDate;

    // @JsonProperty("수령일")
    @Column(nullable = false, length = 10)
    private String receiptDate;

    // @JsonProperty("수령점명")
    @Column(nullable = false, length = 30)
    private String receiptLocation;

    // @JsonProperty("가상계좌입금일자")
    @Column(nullable = false, length = 10)
    private String virtualAccountDepositDate;

    // @JsonProperty("가상입금계좌번호")
    @Column(nullable = false, length = 20)
    private String virtualDepositAccount;

    // @JsonProperty("가상계좌입금금액")
    @Column(nullable = false, length = 20)
    private String virtualAccountDepositAmount;

    // @JsonProperty("가상입금기한일자")

    @Column(nullable = false, length = 10)
    private String deadlineDate;

    // @JsonProperty("가상입금기한시각")
    @Column(nullable = false, length = 10)
    private String deadlineTime;

    // @JsonProperty("환전구분")
    @Column(nullable = false, length = 10)
    private String exchangeType;

    // @JsonProperty("환전통화")
    @Column(nullable = false, length = 5)
    private String currency;

    // @JsonProperty("환전금액")
    @Column(nullable = false, length = 20)
    private String exchangeAmount;

    // @JsonProperty("우대적용환율")
    @Column(nullable = false, length = 10)
    private String preferentialRate;

    // @JsonProperty("원화금액")
    @Column(nullable = false, length = 20)
    private String KRWAmount;
}
