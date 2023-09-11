import React from 'react';
import './DiaryMain.scss';

export default function Diary() {
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <>
      <header>
        <nav className="diaryLogo">
          <section className="logoArea">
            <img src="../../../assets/icons/shinhan-symbol.png" alt="신한" />
            <div>Triplet</div>
          </section>
          <section className="settings">
            <form className="userInfo" action="POST" method={handleSubmit}>
              <div>신한 해커톤</div>
              <button>로그아웃</button>
            </form>
            <img
              src="../../../assets/icons/setting.png"
              width="30vw"
              alt="환경설정"
            />
          </section>
        </nav>
      </header>
      <main>
        <section className="moneyArea">
          <section className="infoArea">
            <h1>여행지 이름</h1>
            <h2>프로젝트 이름</h2>
          </section>
          <section className="dashboard">
            <div className="dashText">Dashboard</div>
            <section className="boardArea">
              <section className="spentMoney">
                <div className="viewCurrency">₩</div>
                <article>
                  <div className="moneyPart">276490</div>
                  <div className="moneyDesc">총 지출</div>
                </article>
              </section>
              <section className="restMoney">
                <div className="viewCurrency">₩</div>
                <article>
                  <div className="moneyPart">573510</div>
                  <div className="moneyDesc">남은 예산</div>
                </article>
              </section>
              <section className="restCash">
                {/* 고정 아님 */}
                <div className="viewCurrency">$</div>
                <article>
                  <div className="moneyPart">400</div>
                  <div className="moneyDesc">남은 현금</div>
                </article>
              </section>
            </section>
          </section>
        </section>
        <section className="calArea">
          <ul className="calHeader">
            {day.map((v) => (
              <li className={'calLi' + v} key={v}>
                {v}
              </li>
            ))}
          </ul>
          <ul></ul>
        </section>
      </main>
    </>
  );
}
