import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import './DiaryMain.scss';
import { getCookie, removeCookie } from '../../../api/cookie';
import { logoutUser } from '../../../api/AccountApis';
import { useMutation } from '@tanstack/react-query';
import DiaryFractal from '../DiaryFractal/DiaryFractal';

export default function DiaryMain() {
  const [diaryFractals, setDiaryFractals] = useState([]);
  // 날짜 넣기 위한 Day 배열
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];

  const navigate = useNavigate();
  const { tripId } = useParams(); // /trips/:tripsId/dailies
  const tripInfo = useLocation().state;
  let tripStart = '',
    tripEnd = '',
    spaceDate = '';

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  useEffect(() => {
    tripStart = getWantedWeek(tripInfo.startDate, true);
    tripEnd = getWantedWeek(tripInfo.endDate, false);
    spaceDate =
      (new Date(tripEnd) - new Date(tripStart)) / (1000 * 60 * 60 * 24);
    console.log(spaceDate);
    const fractals = Array.from({ length: spaceDate + 1 }, (_, index) => (
      <DiaryFractal key={index} />
    ));

    setDiaryFractals(fractals);
    console.log('-------------------------');
    console.log(diaryFractals);
  }, []);

  // 주차 시작 및 끝 날짜 구하기
  const getWantedWeek = (dateString, isStart) => {
    const date = new Date(dateString);
    const dayOfWeek = date.getDay();
    const wantedWeek = new Date(date);
    if (isStart) wantedWeek.setDate(date.getDate() - dayOfWeek);
    else wantedWeek.setDate(date.getDate() + (6 - dayOfWeek));

    const year = wantedWeek.getFullYear();
    const month = String(wantedWeek.getMonth() + 1).padStart(2, '0');
    const day = String(wantedWeek.getDate()).padStart(2, '0');

    console.log(`wantedWeek : ${wantedWeek}`);
    console.log(`wantedWeek의 연월일 : ${year}-${month}-${day}`);
  };

  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));

  const userLogout = useMutation(() => logoutUser(), {
    onSuccess: (data) => {
      removeCookie('name');
      removeCookie('JSESSIONID');
      navigate('/');
    },
  });

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
              <div>{userName}</div>
              <button onClick={() => userLogout.mutate()}>로그아웃</button>
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
          {/* 반복문 개수만큼 fractal 만들기 */}
          <ul className="calSection"></ul>
        </section>
      </main>
    </>
  );
}
