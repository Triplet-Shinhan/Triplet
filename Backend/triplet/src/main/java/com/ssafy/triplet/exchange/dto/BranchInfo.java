package com.ssafy.triplet.exchange.dto;

import lombok.Data;

@Data
public class BranchInfo implements Comparable<BranchInfo> {

    private String branchName;

    private String areaCode;

    private String address;

    private String telNumber;

    private String latitude;

    private String longitude;

    private Double distance;

    @Override
    public int compareTo(BranchInfo next) {
        return (int) (this.getDistance() - next.getDistance());
    }
}