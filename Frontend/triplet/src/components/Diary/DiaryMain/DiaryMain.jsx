import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import './DiaryMain.scss';
import { getCookie } from '../../../api/cookie';
import { logoutUser } from '../../../api/BankAccountApis';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import getWantedWeek from '../../../utils/getWantedWeek';
import makeDots from '../../../utils/makeDots';
import DiaryList from '../DiaryList/DiaryList';

export default function DiaryMain() {
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const [tripStart, setTripStart] = useState('');
  const [tripEnd, setTripEnd] = useState('');
  const [spaceDate, setSpaceDate] = useState(0);
  // 날짜 넣기 위한 Day 배열
  const navigate = useNavigate();
  const { tripId } = useParams(); // /trips/:tripsId/dailies
  const { diary } = useDiaryApi();
  const tripInfo = useLocation().state;

  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));
  const queryClient = useQueryClient();

  // 서버에서 trip에 관련한거 전부 가져오기
  const {
    isLoading,
    error,
    data: tripData,
  } = useQuery(['diaryMain', tripId], () => diary.viewDiaryDetail({ tripId }), {
    enabled: !!tripId,
    staleTime: 1000 * 6 * 5,
  });

  useEffect(() => {
    queryClient.invalidateQueries(['diaryMain', tripId]);
    setSpaceDate((new Date(tripEnd) - new Date(tripStart)) / (1000 * 60 * 60 * 24));
  }, [tripId, tripData]);

  useEffect(() => {
    setTripStart(getWantedWeek(tripInfo.startDate, true));
    setTripEnd(getWantedWeek(tripInfo.endDate, false));
  }, [tripInfo]);

  const userLogout = useMutation(() => logoutUser(), {
    onSuccess: () => {
      sessionStorage.removeItem('isLoggedIn');
      navigate('/login');
    },
    onError: (error) => {
      console.log(error);
    },
  });

  return (
    <>
      {tripData && (
        <div>
          <header>
            <nav className="diaryLogo">
              <section className="logoArea">
                <img src="/assets/icons/shinhan-symbol.webp" alt="신한" />
                <div>Triplet</div>
              </section>
              <section className="settings">
                <div>{userName}님</div>
                <button className="logout" onClick={() => userLogout.mutate()}>
                  로그아웃
                </button>
                <button className="settingBtn">
                  <img
                    src="/assets/icons/setting.webp"
                    width="30vw"
                    alt="환경설정"
                    onClick={() =>
                      navigate(`/trips/${tripId}/dailies/setting`, {
                        state: tripInfo.startDate,
                      })
                    }
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
                      <div className="moneyPart">{makeDots(tripData[0].dashboard.sumExpenditure)}</div>
                      <div className="moneyDesc">총 지출</div>
                    </article>
                  </section>
                  <section className="restMoney">
                    <div className="viewCurrency">₩</div>
                    <article>
                      <div className="moneyPart">{makeDots(tripData[0].dashboard.budget)}</div>
                      <div className="moneyDesc">남은 예산</div>
                    </article>
                  </section>
                  <section className="restCash">
                    <div className="viewCurrency">{tripData[0].dashboard.currencySymbol}</div>
                    <article>
                      <div className="moneyPart">{makeDots(tripData[0].dashboard.cash)}</div>
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
              {tripData && (
                <DiaryList
                  spaceDate={spaceDate}
                  tripInfo={tripInfo}
                  tripId={tripId}
                  tripStart={tripStart}
                  tripData={tripData[0]}
                />
              )}
            </section>
          </main>
        </div>
      )}
    </>
  );
}
