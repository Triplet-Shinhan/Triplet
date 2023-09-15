import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import './DiaryMain.scss';
import { getCookie, removeCookie } from '../../../api/cookie';
import { logoutUser } from '../../../api/BankAccountApis';
import { useMutation, useQuery } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import DiaryFractal from '../DiaryFractal/DiaryFractal';

export default function DiaryMain() {
  const [diaryFractals, setDiaryFractals] = useState([]);
  // 날짜 넣기 위한 Day 배열
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const navigate = useNavigate();
  const { tripId } = useParams(); // /trips/:tripsId/dailies
  const { diary } = useDiaryApi();
  const tripInfo = useLocation().state;
  let tripStart = '',
    tripEnd = '';
  let spaceDate = 0;
  let isValid = false;
  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));

  // 서버에서 trip에 관련한거 전부 가져오기
  const {
    isLoading,
    error,
    data: tripData,
  } = useQuery(
    ['diaryMain', tripId],
    () => diary.viewDiaryDetail({ tripId }),
    {
      enabled: !!tripId,
    },
    { staleTime: 1000 * 6 * 5 }
  );

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  useEffect(() => {
    tripStart = getWantedWeek(tripInfo.startDate, true);
    tripEnd = getWantedWeek(tripInfo.endDate, false);
    spaceDate =
      (new Date(tripEnd) - new Date(tripStart)) / (1000 * 60 * 60 * 24);
    let dayNum = 0;
    const fractals = Array.from({ length: spaceDate + 1 }, (_, index) => (
      <DiaryFractal
        key={index}
        date={putDate(tripStart, index)}
        tripId={tripId}
        isValid={isValid}
        dailyInfo={
          isValid && tripData !== undefined && tripData.dailies[dayNum++]
        }
      />
    ));
    console.log(tripData);
    setDiaryFractals(fractals);
  }, [tripId]);

  new Date(tripInfo.startDate).getDay();

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

    return `${year}-${month}-${day}`;
  };

  // 다이어리 조각 만들기
  const putDate = (dateString, start) => {
    const date = new Date(dateString);
    const wantedWeek = new Date(date);
    wantedWeek.setDate(date.getDate() + start);
    const month = String(wantedWeek.getMonth() + 1).padStart(2, '0');
    const day = String(wantedWeek.getDate()).padStart(2, '0');
    isValid =
      wantedWeek.getTime() >= new Date(tripInfo.startDate).getTime() &&
      wantedWeek.getTime() <= new Date(tripInfo.endDate).getTime();

    return `${month}.${day}`;
  };

  const userLogout = useMutation(() => logoutUser(), {
    onSuccess: (data) => {
      removeCookie('name');
      removeCookie('JSESSIONID');
    },
  });

  if (isLoading) {
    return <div>loading...</div>;
  }
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
              <div>{userName}님</div>
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
            <h1 className="infoH1">{tripInfo.location}</h1>
            <h2 className="infoH2">{tripInfo.prjName}</h2>
          </section>
          <section className="dashboard">
            <div className="dashText">Dashboard</div>
            <section className="boardArea">
              <section className="spentMoney">
                <div className="viewCurrency">₩</div>
                <article>
                  <div className="moneyPart">
                    {tripData.dashboard.sumExpenditure}
                  </div>
                  <div className="moneyDesc">총 지출</div>
                </article>
              </section>
              <section className="restMoney">
                <div className="viewCurrency">₩</div>
                <article>
                  <div className="moneyPart">{tripData.dashboard.budget}</div>
                  <div className="moneyDesc">남은 예산</div>
                </article>
              </section>
              <section className="restCash">
                <div className="viewCurrency">$</div>
                <article>
                  <div className="moneyPart">{tripData.dashboard.cash}</div>
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
          <ul className="calSelection">{diaryFractals}</ul>
        </section>
      </main>
    </>
  );
}
