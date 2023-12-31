package com.ssafy.triplet.daily.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.triplet.payment.domain.Payment;
import com.ssafy.triplet.trip.domain.Trip;
import com.ssafy.triplet.user.domain.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "daily")
@AllArgsConstructor
@RequiredArgsConstructor
public class Daily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dailyId;

	@ManyToOne
	@JoinColumn(name = "user_id")//해당 유저의 userId를 외래키로 가져온다.
	private User user;

	@ManyToOne
	@JoinColumn(name = "trip_id")//해당 유저의 tripId를 외래키로 가져온다.
	private Trip trip;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@Column
	private String imageUrl;

	@OneToMany(mappedBy = "daily", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Payment> payments;
}
