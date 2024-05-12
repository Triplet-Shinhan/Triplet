import { useMutation } from '@tanstack/react-query';
import './Signup.scss';
import { checkAccount, signupUser } from '../../../api/BankAccountApis';

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Signup() {
  let isChecked = false;
  const [accountName, setAccountName] = useState(''); // 1원이체 계좌
  const [inputName, setInputName] = useState(''); // 입금자명
  const [formData, setFormData] = useState({
    name: '',
    birth: '',
    email: '',
    password: '',
    phoneNum: '',
    accountNum: '',
  });

  const navigate = useNavigate();

  // 서버로 이름이랑 계좌번호보내기
  const handleAccount = (e) => {
    e.preventDefault();
    verifyAccountMutation.mutate({
      name: formData.name,
      accountNum: formData.accountNum,
    });
  };

  // 이름이랑 계좌번호 같은지 확인하는 함수
  const verifyAccountMutation = useMutation(({ name, accountNum }) => checkAccount({ name, accountNum }), {
    // 데이터 보내주는지 확인하기
    onSuccess: (data) => {
      // 1원 계좌 확인
      // 성공시 resultCode 200번
      console.log('success');
      console.log(data);
      console.log(data.data.memo);

      setAccountName(data.data.memo);
    },
    onError: () => {
      console.log('동일하지 않습니다.');
    },
  });

  // 회원가입 잘 되는지 확인하는 함수
  const signupUserMutation = useMutation(() => signupUser({ formData }), {
    onSuccess: () => {
      // 회원가입 잘 되면 코드 성공 코드 200번
      console.log('success');
      navigate('/');
    },
    onError: () => {
      console.log('회원가입이 되지 않았습니다.');
    },
  });

  // form 제출했을 때
  const handleSubmit = (e) => {
    e.preventDefault();

    // 형식에 맞는 정규식
    const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const birthReg = /^\d{8}$/; // 숫자 8개만 가능
    const phoneReg = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
    const passReg = /^[a-zA-Z0-9_*!@#$%^&(){}\]:;<>,.?~\\+=|~-]{8,15}$/;
    const nameReg = /[가-힣]+/;

    // 정규식 기준에 올바른지 테스트
    if (
      emailReg.test(formData.email) &&
      nameReg.test(formData.name) &&
      birthReg.test(formData.birth) &&
      phoneReg.test(formData.phoneNum) &&
      passReg.test(formData.password)
    ) {
      console.log(formData);
      signupUserMutation.mutate({ formData });
      console.log('회원가입 완료!');
    } else if (!emailReg.test(formData.email)) {
      console.log('이메일 형식에 맞게 입력해주세요.');
      return;
    } else if (!nameReg.test(formData.name)) {
      console.log('이름 형식에 맞게 입력해주세요.');
      return;
    } else if (!birthReg.test(formData.birth)) {
      console.log('숫자만 입력해주세요.');
      return;
    } else if (!phoneReg.test(formData.phoneNum)) {
      console.log('올바른 휴대폰 번호를 입력해주세요');
      return;
    } else {
      console.log('비밀번호 형식에 맞게 입력해주세요');
      return;
    }
  }; // handleSubmit 끝

  // 입력받은 입금자명과 1원계좌의 이름이 같은지 확인하는 함수
  const checkSame = (e) => {
    e.preventDefault();
    console.log(inputName);
    if (accountName === inputName) isChecked = true;
    else alert('입금자명이 일치하지 않습니다.');
  };

  // 값이 변할 때 추적하기 위한 함수
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // 비밀번호가 같은지 확인하기 위한 함수
  const handleRight = (e) => {
    if (e.target.value === formData.password) {
      console.log('같은 비밀번호입니다.');
    } else console.log('다른 비밀번호입니다.');
  };

  return (
    <div className="signupBackground">
      <img className="signupImage" src="/assets/airplane.webp" alt="배경 이미지" />
      <main className="signupMainContainer">
        <h1>사용자 등록</h1>
        <form id="signupForm" className="signupForm" action="POST" onSubmit={handleSubmit}>
          <div className="signupInputArea">
            <input
              type="email"
              name="email"
              className="email"
              placeholder="이메일"
              value={formData.email}
              onChange={handleChange}
              required
            />
            <input type="text" name="name" placeholder="이름" value={formData.name} onChange={handleChange} required />
            <input
              type="text"
              name="birth"
              placeholder="생년월일 8글자"
              value={formData.birth}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="phoneNum"
              placeholder="전화번호(-제외)"
              value={formData.phoneNum}
              onChange={handleChange}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="비밀번호"
              value={formData.password}
              onChange={handleChange}
              required
            />
            <input type="password" className="passwordCo" placeholder="비밀번호 확인" onChange={handleRight} required />
          </div>
        </form>

        {/* 계좌번호 확인 form */}
        <div className="authArea">
          <div className="formArea">
            <form id="authForm" className="authForm" action="POST" onSubmit={handleAccount}>
              <input
                type="text"
                className="accArea"
                name="accountNum"
                placeholder="계좌번호(-제외)"
                value={formData.accountNum}
                onChange={handleChange}
                required
              />
            </form>

            {/* 입금자명 확인 form */}
            <form id="confirmForm" className="confirmForm" action="POST" onSubmit={checkSame}>
              <input
                type="text"
                className="verifyCode"
                placeholder="인증코드(입금자명)을 입력해주세요."
                value={inputName}
                onChange={(e) => setInputName(e.target.value)}
              />
            </form>
          </div>
          <div className="btnArea">
            <button className="authBtn" form="authForm">
              인증
            </button>
            <button className="confirmBtn" form="confirmForm">
              확인
            </button>
          </div>
        </div>
        <button className="registerBtn" form="signupForm">
          회원가입
        </button>
      </main>
    </div>
  );
}
