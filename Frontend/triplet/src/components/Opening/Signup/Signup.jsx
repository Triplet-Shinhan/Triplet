import './Signup.scss';

import React, { useState } from 'react';

// To do
// 백으로 쿼리 보내기
//

export default function Signup() {
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [birth, setBirth] = useState('');
  const [phone, setPhone] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // 형식에 맞는 정규식
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const birthReg = /^\d{8}$/; // 숫자 8개만 가능
    const phoneReg = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
    const passReg = /^[a-z0-9_]{8,15}$/;
    const nameReg = /[가-힣]/;

    // 정규식 기준에 올바른지 테스트
    if (
      emailReg.test(email) &&
      nameReg.test(name) &&
      birthReg.test(birth) &&
      phoneReg.test(phone) &&
      passReg.test(password)
    ) {
      // 백으로 쿼리 보내기
      console.log('회원가입 완료!');
    } else if (!emailReg.test(email)) {
      console.log('이메일 형식에 맞게 입력해주세요.');
      return;
    } else if (!nameReg.test(name)) {
      console.log('이름 형식에 맞게 입력해주세요.');
      return;
    } else if (!birthReg.test(birth)) {
      console.log('숫자만 입력해주세요.');
      return;
    } else if (!phoneReg.test(phone)) {
      console.log('올바른 휴대폰 번호를 입력해주세요');
      return;
    } else {
      console.log('비밀번호 형식에 맞게 입력해주세요');
      return;
    }

    // 폼 보내고 난 이후 초기화
    setEmail('');
    setName('');
    setBirth('');
    setPhone('');
    setPassword('');
  };

  // 값이 변할 때 추적하기 위한 함수
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleBirthChange = (e) => {
    setBirth(e.target.value);
  };

  const handlePhoneChange = (e) => {
    setPhone(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  // 비밀번호가 동일한지 확인하기 위한 함수
  const handleRight = (e) => {
    if (e.target.value === password) {
      console.log('같은 비밀번호입니다.');
    } else console.log('다른 비밀번호입니다.');
  };

  return (
    <main>
      <h1>사용자 등록</h1>
      <form action="post" onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={handleEmailChange}
        />
        <input
          type="text"
          placeholder="이름"
          value={name}
          onChange={handleNameChange}
        />
        <input
          type="text"
          placeholder="생년월일 8글자"
          value={birth}
          onChange={handleBirthChange}
        />
        <input
          type="text"
          placeholder="전화번호(-제외)"
          value={phone}
          onChange={handlePhoneChange}
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={handlePasswordChange}
        />
        <input
          type="password"
          placeholder="비밀번호 확인"
          onChange={handleRight}
        />
        <button>회원가입</button>
      </form>
      <form action="post" type="submit">
        <input type="number" placeholder="계좌번호(-제외)" />
        <button>인증</button>
      </form>
      <form action="post" type="submit">
        <input type="text" placeholder="인증코드(입금자명)을 입력해주세요." />
        <button>확인</button>
      </form>
    </main>
  );
}
