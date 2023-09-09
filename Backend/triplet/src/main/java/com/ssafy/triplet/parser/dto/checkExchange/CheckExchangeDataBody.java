package com.ssafy.triplet.parser.dto.checkExchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class CheckExchangeDataBody {
    @JsonProperty("리스트건수")
    private Long listNum;

    @JsonProperty("리스트")
    private List<ExchangeResult> list;
}
