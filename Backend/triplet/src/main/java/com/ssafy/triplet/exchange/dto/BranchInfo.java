package com.ssafy.triplet.exchange.dto;

import lombok.Data;

@Data
public class BranchInfo {

    private String branchName;

    private String areaCode;

    private String address;

    private String telNumber;

    private Double latitude;

    private Double longitude;
}