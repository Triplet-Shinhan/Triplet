package com.ssafy.triplet.auth.service;

import org.springframework.stereotype.Service;

import com.ssafy.triplet.auth.dto.OneTransferRequestDto;
import com.ssafy.triplet.parser.WebClientUtil;
import com.ssafy.triplet.parser.dto.checkAccount.AuthRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final WebClientUtil webClientUtil;
	private final String SHINHAN_BANKCODE = "088";

	public void sendOne(OneTransferRequestDto request, String memo) {
		AuthRequestDto authRequestDto = new AuthRequestDto();
		authRequestDto.setBankCode(SHINHAN_BANKCODE);
		authRequestDto.setAccountNum(request.getAccount());
		authRequestDto.setMemo(memo);
		webClientUtil.checkAccount(authRequestDto);
	}
}
