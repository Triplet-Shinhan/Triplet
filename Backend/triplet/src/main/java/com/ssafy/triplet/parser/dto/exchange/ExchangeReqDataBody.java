package com.ssafy.triplet.parser.dto.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeReqDataBody {
    @JsonProperty("serviceCode")
    private String serviceCode;

    @JsonProperty("환전통화")
    private String currency;

    @JsonProperty("거래자성명")
    private String name;

    @JsonProperty("환전금액")
    private String exchangeAmount;

    @JsonProperty("수령처")
    private String location;

    @JsonProperty("수령일자")
    private String receiveDate;

    @JsonProperty("수령인성명")
    private String receiverName;

    @JsonProperty("생년월일")
    private String birth;

    @JsonProperty("휴대폰번호")
    private String phoneNum;

    @JsonProperty("환전수령방법")
    private String receiveWay;
}
