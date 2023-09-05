import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.scss';

// To do
// 백으로 쿼리 보내기

export default function Login() {
  const [email, setEmail] = useState('');
  const [phoneNum, setPhoneNum] = useState('');

  const navigate = useNavigate();

  // form 제출했을 때
  const handleSubmit = (e) => {
    e.preventDefault();

    // 형식에 맞는 정규식
    const phoneReg = /^\d{2,3}-\d{3,4}-\d{4}$/g;
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // 정규식 기준에 올바른지 테스트
    if (phoneReg.test(phoneNum) && emailReg.test(email)) {
      // 백으로 쿼리 보내기
      console.log('로그인 완료');
    } else if (!phoneReg.test(phoneNum)) {
      console.log('휴대폰 번호를 형식에 맞게 입력해주세요.');
      return;
    } else {
      console.log('이메일 형식에 맞게 입력해주세요.');
      return;
    }
    // 폼 보내고 난 후 초기화
    setEmail('');
    setPhoneNum('');
  };

  // 값이 변할 때 추적하기 위한 함수
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePhoneChange = (e) => {
    setPhoneNum(e.target.value);
  };

  return (
    <main>
      <h1>새로운 여행 플랫폼의 시작</h1>
      <h2>Triplet</h2>
      <form onSubmit={handleSubmit}>
        <input
          id="email"
          type="email"
          placeholder="이메일"
          value={email}
          onChange={handleEmailChange}
        />
        <input
          id="phoneNum"
          type="text"
          placeholder="전화번호(-를 포함하여 작성해주세요)"
          value={phoneNum}
          onChange={handlePhoneChange}
        />
        <button>로그인</button>
      </form>
      <button onClick={() => navigate('/signup')}>아이디가 없다면?</button>
    </main>
  );
}
