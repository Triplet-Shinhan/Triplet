import React from 'react';
import './DiaryMain.scss';

export default function Diary() {
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <>
      <header>
        <nav className="diaryLogo">
          <img
            src="../../../assets/icons/shinhan-symbol.png"
            width="30vw"
            alt="신한"
          />
          <div>Triplet</div>
          <form action="POST" method={handleSubmit}>
            <div>신한 해커톤</div>
            <button>로그아웃</button>
          </form>
          <img
            src="../../../assets/icons/setting.png"
            width="30vw"
            alt="환경설정"
          />
        </nav>
      </header>
      <main></main>
    </>
  );
}
