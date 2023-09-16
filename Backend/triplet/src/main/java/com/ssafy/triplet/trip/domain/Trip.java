package com.ssafy.triplet.trip.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.triplet.daily.domain.Daily;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.user.domain.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Table(name = "trip")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private Long tripId;

	@ManyToOne// N : 1 관계
	@JoinColumn(name = "user_id")//해당 유저의 userId를 외래키로 가져온다.
	private User user;

	@OneToMany(mappedBy = "trip", cascade = CascadeType.PERSIST)
	private List<Payment> payments;

	@Column(nullable = false)
	private String prjName;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private Long budget;

	@Column(nullable = false)
	private Long exchangedBudget;

	@Column(nullable = false)
	private Long usedBudget;

	@Column(nullable = false)
	private String currency;

	@Column(nullable = false)
	private Float fixedRate;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Daily> dailies;
}
