import React from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import './DiaryMain.scss';

export default function Diary() {
  // 날짜 넣기 위한 Day 배열
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];

  const navigate = useNavigate();
  const { tripId } = useParams(); // /trips/:tripsId/dailies
  const tripInfo = useLocation().state;

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  // 요일 구하는 함수 (0이 일요일 6이 토요일)
  const getDayOfWeek = (startDate) => new Date(startDate).getDay();

  // 주차 시작 날짜 구하기
  const startCal = () => {
    const startDate = getDayOfWeek(tripInfo.startDate);
    const endDate = getDayOfWeek(tripInfo.endDate);

    const adjustStartDate = (date, daysToDelete) => {
      const adjustedDate = new Date(date);
      adjustedDate.setDate(adjustedDate.getDate() - daysToDelete);
      return adjustedDate;
    };

    if (startDate !== 0) {
      const adjustedStartDate = adjustStartDate(
        tripInfo.startDate,
        7 - startDate
      );
      return adjustedStartDate;
    } else return tripInfo.startDate;
  };

  let tripStart = startCal(),
    tripEnd = 0;

  console.log(tripStart);
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
            <button>
              <img
                src="../../../assets/icons/setting.png"
                width="30vw"
                alt="환경설정"
                onClick={() => navigate(`/trips/${tripId}/dailies/setting`)}
              />
            </button>
          </section>
        </nav>
      </header>
      <main>
        <section className="moneyArea">
          <section className="infoArea">
            <h1>{tripInfo.location}</h1>
            <h2>{tripInfo.prjName}</h2>
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
          <ul className="calSection">
            <li></li>
          </ul>
        </section>
      </main>
    </>
  );
}
