import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.scss';
import { loginUser } from '../../../api/BankAccountApis';
import { useMutation } from '@tanstack/react-query';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // 로그인
  const goLogin = useMutation(
    ({ email, password }) => loginUser({ email, password }),
    {
      onSuccess: (data) => {
        if ((data.email = email)) {
          sessionStorage.setItem('user_id', email);
        }
        navigate('/trips');
      },
    }
  );

  // form 제출했을 때
  const handleSubmit = (e) => {
    e.preventDefault();

    // 형식에 맞는 정규식
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // 정규식 기준에 올바른지 테스트
    if (emailReg.test(email)) {
      // 백으로 쿼리 보내기
      goLogin.mutate({ email, password });
    }
  };

  // 값이 변할 때 추적하기 위한 함수
  const handleChange = (e, setter) => {
    setter(e.target.value);
  };

  return (
    <div className="loginBackground">
      <main className="loginMainContainer">
        <section className="logoArea">
          <img
            className="logoImg"
            src="../../../assets/icons/shinhan-symbol.png"
            alt="logo"
          />
          <div className="logo">Triplet</div>
        </section>
        <secion className="loginMainSec">
          <h1 className="loginH1">새로운 여행 플랫폼의 시작</h1>
          <h2 className="loginH2">Triplet</h2>
          <form className="loginForm" onSubmit={handleSubmit}>
            <div className="loginInputArea">
              <input
                id="email"
                type="email"
                placeholder="이메일"
                className="email"
                value={email}
                onChange={(e) => handleChange(e, setEmail)}
                required
              />
              <input
                id="password"
                type="password"
                className="password"
                placeholder="비밀번호"
                value={password}
                onChange={(e) => handleChange(e, setPassword)}
                required
              />
            </div>
            <button className="loginBtn">로그인</button>
          </form>
          <button className="registerBtn" onClick={() => navigate('/signup')}>
            아이디가 없다면?
          </button>
        </secion>
      </main>
    </div>
  );
}
