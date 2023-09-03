package com.ssafy.triplet.exchange.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearBranchDto {

    private int resultCode;
    private int listNum;
    private List<BranchInfo> dataList;

    @Data
    private static class BranchInfo {
        @JsonAlias("지역명")
        private String areaName;

        @JsonAlias("지역코드")
        private String areaCode;

        @JsonAlias("지점주소")
        private String address;

        @JsonAlias("지점대표전화번호")
        private String telNumber;

        @JsonAlias("지점위도")
        private double latitude;

        @JsonAlias("지점경도")
        private double longtitude;
    }
}
