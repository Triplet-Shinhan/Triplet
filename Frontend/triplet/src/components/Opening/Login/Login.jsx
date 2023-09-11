import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { AxiosInstance } from '../../../api/AccountApis';
import './Login.scss';
import axios from 'axios';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  // form 제출했을 때
  const handleSubmit = (e) => {
    e.preventDefault();

    // 형식에 맞는 정규식
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // 정규식 기준에 올바른지 테스트
    if (emailReg.test(email)) {
      // 백으로 쿼리 보내기

      AxiosInstance.post('/users/login', {
        email,
        password,
      }).then((res) => {
        console.log(res);

        if (res.data.email == email) {
          sessionStorage.setItem('user_id', email);
        }
        navigate('/trips');
      });
    } else {
      console.log('이메일 형식에 맞게 입력해주세요.');
      return;
    }

    // 폼 보내고 난 후 초기화
    // setEmail('');
    // setPassword('');
  };

  // 값이 변할 때 추적하기 위한 함수
  const handleChange = (e, setter) => {
    setter(e.target.value);
  };

  return (
    <main className="loginMain">
      <h1>새로운 여행 플랫폼의 시작</h1>
      <h2>Triplet</h2>
      <form onSubmit={handleSubmit}>
        <input
          id="email"
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => handleChange(e, setEmail)}
          required
        />
        <input
          id="password"
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => handleChange(e, setPassword)}
          required
        />
        <button>로그인</button>
      </form>
      <button onClick={() => navigate('/signup')}>아이디가 없다면?</button>
    </main>
  );
}
