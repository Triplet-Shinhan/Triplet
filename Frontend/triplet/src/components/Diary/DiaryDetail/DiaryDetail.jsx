import React, { useEffect } from 'react';
import './DiaryDetail.scss';
import { useLocation, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';

export default function DiaryDetail() {
  const { diary } = useDiaryApi();
  const { tripId, dailyId } = useParams();
  const dailyInfo = useLocation().state; // dailies 하나의 정보가 들어있음
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const dateInfo = dailyInfo.date.substr(5).split('-').join('.');
  const weekInfo = day[new Date(dailyInfo.date).getDay()];

  const {
    isLoading,
    error,
    data: expendList,
  } = useQuery(
    ['diaryDetail'],
    () => diary.getExpendList({ tripId, dailyId }),
    { staleTime: 1000 * 6 * 5 }
  );

  return (
    <div className="diaryDetailPage">
      <header className="detailHeader">
        <nav className="detailName">Triplet</nav>
      </header>
      <main className="detailMain">
        <section className="expendSec">
          <section className="loginSec"></section>
          <section className="viewSec">
            <div>DAY1</div>
            <div>{dateInfo}</div>
            <div>{weekInfo}</div>
            <div>{dailyInfo.sum}</div>
          </section>
          <section className="eachSec"></section>
        </section>
        <section className="imgSec">
          <img className="imgSrc" src={dailyInfo.imageUrl} />
        </section>
      </main>
    </div>
  );
}
