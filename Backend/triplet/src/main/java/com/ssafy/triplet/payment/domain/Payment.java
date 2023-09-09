package com.ssafy.triplet.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_Id")
    private Long paymentId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "daily_id")
    private Long dailyId;

    @Column(name = "item")
    private String item;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "foreign_currency")
    private String foreignCurrency;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "current_rate")
    private Float currentRate;

    @Column(name = "method")
    private String method;
}