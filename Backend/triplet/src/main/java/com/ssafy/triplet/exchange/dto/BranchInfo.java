package com.ssafy.triplet.exchange.dto;

import lombok.Data;

@Data
public class BranchInfo implements Comparable<BranchInfo> {

    private String branchName;

    private String areaCode;

    private String address;

    private String telNumber;

    private Double latitude;

    private Double longitude;

    private Double distance;

    @Override
    public int compareTo(BranchInfo next) {
        return (int) (this.getDistance() - next.getDistance());
    }
}