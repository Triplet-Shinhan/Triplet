package com.ssafy.triplet.daily.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.triplet.trip.domain.Trip;
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
@Table(name = "daily")
@AllArgsConstructor
@RequiredArgsConstructor
public class Daily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	private Long dailyId;

	@ManyToOne// N : 1 관계
	@JoinColumn(name = "user_id", referencedColumnName = "userId")//해당 유저의 userId를 외래키로 가져온다.
	private User user;

	@ManyToOne// N : 1 관계
	@JoinColumn(name = "trip_id", referencedColumnName = "tripId")//해당 유저의 tripId를 외래키로 가져온다.
	private Trip trip;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	@Column(nullable = false)
	private String imageUrl;
}
