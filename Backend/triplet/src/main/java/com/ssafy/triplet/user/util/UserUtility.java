package com.ssafy.triplet.user.util;

import org.springframework.stereotype.Component;

import com.ssafy.triplet.exception.BaseException;
import com.ssafy.triplet.exception.ErrorCode;
import com.ssafy.triplet.user.domain.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class UserUtility {
	public User getUserFromCookie(HttpServletRequest request) {
		String jsessionId = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("JSESSIONID".equals(cookie.getName())) {
					jsessionId = cookie.getValue();
					break;
				}
			}
		}

		if (jsessionId != null) {
			HttpSession session = request.getSession(false); // 현재 세션을 가져오기
			if (session != null && jsessionId.equals(session.getId())) {
				User user = (User)session.getAttribute("user");
				if (user != null) {
					return user;
				}
				throw new BaseException(ErrorCode.USER_ID_NOT_FOUND);
			}
		}
		throw new BaseException(ErrorCode.COOKIE_NOT_FOUND);
	}
}
