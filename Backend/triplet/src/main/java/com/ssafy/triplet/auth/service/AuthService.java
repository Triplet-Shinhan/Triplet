package com.ssafy.triplet.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.auth.dto.AuthResponseDto;
import com.ssafy.triplet.auth.dto.OneTransferRequestDto;
import com.ssafy.triplet.auth.util.AuthUtility;

import lombok.RequiredArgsConstructor;

/*
1. 프론트에서 (“이름”,”계좌번호”) 백엔드단으로 전송하고 백엔드에서 랜덤한 입금자명 알려줌
2. 백에서 해당정보로 신한api(입금자명은 백에서지정)에 전송
3. 신한api는 해당계좌로 1원이체
4. 사용자는 프론트단에서 입금자명입력
5. 프론트는 사용자입력이 맞는지 확인
 */

@Service
@RequiredArgsConstructor
public class AuthService {
	private AuthUtility authUtility;

	//1원이체 api
	public Optional<AuthResponseDto> getOneTransferResponse(OneTransferRequestDto oneTransferDto) {
		String randomWord = authUtility.getRandomWord();
		return Optional.of(null);
	}
}
