package com.ssafy.triplet.payment.domain;

import com.ssafy.triplet.trip.domain.Daily;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.user.domain.User;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "payment_Id")
    private Long paymentId;

    @ManyToOne
    private User user;//Payment조회시

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private Daily daily;

    @Column(name = "item")
    private String item;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "foreign_currency")
    private String foreignCurrency;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "method")
    private String method;
}