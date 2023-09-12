package com.ssafy.triplet.exchange.util;

import org.springframework.stereotype.Component;

@Component
public class ExchangeUtil {

    // 두 위도, 경도를 통한 계산
    public Double getDistance(Double userLat, Double userLong, Double branchLat, Double branchLong) {
        Double theta = userLong - branchLong;
        Double distance = Math.sin(deg2rad(userLat)) * Math.sin(deg2rad(branchLat))
                + Math.cos(deg2rad(userLat)) * Math.cos(deg2rad(branchLat)) * Math.cos(deg2rad(theta));

        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        // 미터법
        distance *= 1609.344;
        return distance;
    }

    public Double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    public Double rad2deg(Double rad) {
        return (rad * 180 / Math.PI);
    }
}
