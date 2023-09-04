import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.scss';

export default function Login() {
  const [email, setEmail] = useState('');
  const [phoneNum, setPhoneNum] = useState('');

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const handleChange = (e) => {
    if (e.target.id === 'email') {
      setEmail(e.target.value);
    } else setPhoneNum(e.target.value);
  };

  return (
    <main>
      <h1>새로운 여행 플랫폼의 시작</h1>
      <h2>Triplet</h2>
      <form onSubmit={handleSubmit}>
        {/* 이메일 정규식 추가하기 */}
        <input
          id="email"
          type="email"
          placeholder="이메일"
          value={email}
          onChange={handleChange}
        />
        {/* 전화번호 정규식 추가하기 */}
        <input
          id="phoneNum"
          type="text"
          placeholder="전화번호"
          value={phoneNum}
          onChange={handleChange}
        />
        <button>로그인</button>
      </form>
      <button onClick={() => navigate('/signup')}>아이디가 없다면?</button>
    </main>
  );
}
