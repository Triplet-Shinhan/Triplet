package com.ssafy.triplet.trip.domain;

import java.sql.Date;

import com.ssafy.triplet.user.domain.User;

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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "trip")
@AllArgsConstructor
@RequiredArgsConstructor
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private Long tripId;

	@ManyToOne// N : 1 관계
	@JoinColumn(name = "user_id", referencedColumnName = "id")//해당 유저의 userId를 외래키로 가져온다.
	@Column(nullable = false)
	private User user;

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
	private Date startDate;

	@Column(nullable = false)
	private Date endDate;
}
