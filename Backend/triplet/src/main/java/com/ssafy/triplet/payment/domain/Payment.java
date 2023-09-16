package com.ssafy.triplet.payment.domain;

import java.time.LocalDateTime;

import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.trip.domain.Trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "daily_id")
    private Daily daily;

    @Column(name = "item")
    private String item;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "method")
    private String method;
}