package com.ssafy.triplet.parser.dto.checkExchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckExchangeReqDataBody {
    @JsonProperty("serviceCode")
    private String serviceCode;

    @JsonProperty("신청인명")
    private String name;

    @JsonProperty("신청인휴대폰번호")
    private String phoneNum;

    @JsonProperty("신청인생년월일")
    private String birth;

    public CheckExchangeReqDataBody(String name,String phoneNum,String birth){//서비스 코드 파서에서 처리

        this.name=name;
        this.phoneNum=phoneNum;
        this.birth=birth;

    }
}
