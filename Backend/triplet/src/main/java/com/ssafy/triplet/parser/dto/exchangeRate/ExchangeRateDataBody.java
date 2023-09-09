package com.ssafy.triplet.parser.dto.exchangeRate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triplet.parser.dto.exchangeBranch.Branch;
import lombok.Data;

import java.util.List;

@Data
public class ExchangeRateDataBody {

    @JsonProperty("고시일자")
    private String noticeDate;

    @JsonProperty("환율리스트")
    private List<ExchangeRate> exchangeRateList;
}
