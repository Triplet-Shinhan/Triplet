package com.ssafy.triplet.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	// 기본 페이지 요청 메서드
	@GetMapping("/")
	public String index() {
		return "index";
	}


}
