package com.ssafy.triplet.parser.dto.ExchangeBranch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Branch {
    @JsonProperty("지역명")
    private String branchName;

    @JsonProperty("지역코드")
    private String areaCode;

    @JsonProperty("지점주소")
    private String address;

    @JsonProperty("지점대표전화번호")
    private String telNumber;

    @JsonProperty("지점위도")
    private String latitude;

    @JsonProperty("지점경도")
    private String longitude;
}
