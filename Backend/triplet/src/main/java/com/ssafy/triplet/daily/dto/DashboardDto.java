package com.ssafy.triplet.daily.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    @JsonProperty("sumExpenditure")
    private Long sumExpenditure;

    @JsonProperty("budget")
    private Long budget;

    @JsonProperty("cash")
    private Long cash;
}
