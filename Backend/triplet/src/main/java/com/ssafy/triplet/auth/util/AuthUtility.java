package com.ssafy.triplet.auth.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtility {
	private final Random random;
	private final String[] adjectives = {"멋진", "예쁜", "붉은", "슬픈", "기쁜", "작은", "진한", "적은", "빨간", "많은"};
	private final String[] nouns = {"우산", "사과", "하늘", "여름", "구름", "날씨", "풍경", "여행", "얼굴", "바다"};
	private final int WORD_COUNT = 10;

	@Autowired
	public AuthUtility() {
		this.random = new Random();
	}

	public String getRandomWord() {
		String adjective = adjectives[random.nextInt(WORD_COUNT)];
		String noun = nouns[random.nextInt(WORD_COUNT)];
		return adjective + " " + noun;
	}
}
