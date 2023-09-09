package com.ssafy.triplet.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triplet.auth.dto.OneTransferRequestDto;
import com.ssafy.triplet.auth.service.AuthService;
import com.ssafy.triplet.auth.util.AuthUtility;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {
	private final AuthService authService;
	private final AuthUtility authUtility;

	@PostMapping("1transfer")
	public ResponseEntity<Map<String, String>> transferOne(
		@Validated @ModelAttribute OneTransferRequestDto oneTransferRequestDto) {
		String memo = authUtility.getRandomWord();
		authService.sendOne(oneTransferRequestDto, memo);

		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("memo", memo);

		return ResponseEntity.ok(responseMap);
	}
}
