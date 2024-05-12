import React, { useEffect } from 'react';
import './DiaryInto.scss';
import { getCookie } from '../../../api/cookie';
import { useNavigate } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import DiaryPreview from '../DiaryPreview/DiaryPreview';
import { IoIosArrowForward } from 'react-icons/io';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import { logoutUser } from '../../../api/BankAccountApis';
import { useMutation } from '@tanstack/react-query';
import Loading from '../../Loading/Loading';

export default function DiaryInto() {
  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));
  const navigate = useNavigate();
  // 다이어리 API 사용하겠다는 의미
  const { diary } = useDiaryApi();
  const {
    isLoading,
    error,
    data: tripList,
  } = useQuery(
    ['diary'],
    () => {
      return diary.viewProject();
    },
    { staleTime: 1000 * 6 * 5 }
  );

  const userLogout = useMutation(() => logoutUser(), {
    onSuccess: () => {
      sessionStorage.removeItem('isLoggedIn');
      navigate('/login');
    },
    onError: () => {
      console.log(error);
    },
  });

  useEffect(() => {}, [tripList]);

  if (isLoading) {
    return <Loading />;
  }

  return (
    <>
      <div className="header">
        <div className="userInfo">
          <h1 className="diaryInH1">안녕하세요</h1>
          <h2 className="diaryInH2">{userName}님</h2>
        </div>

        <section className="logoArea">
          <section className="logout">
            <button onClick={() => userLogout.mutate()}>로그아웃</button>
          </section>
          <img className="logoImg" src="/assets/icons/shinhan-symbol.webp" alt="logo" />
          <div className="logo">Triplet</div>
        </section>
      </div>
      <ul className="tripInto">
        <li className="tripInfo">
          <nav>프로젝트</nav>
          <div className={tripList === undefined ? 'tripList empty' : 'tripList'}>
            {tripList === undefined ? (
              <button onClick={() => navigate('/trips/setup')}>
                현재 생성된 프로젝트가 없습니다. <br />새 프로젝트를 생성해주세요.
              </button>
            ) : (
              tripList.map((diary, index) => <DiaryPreview diaryInfo={diary} key={index} />)
            )}
          </div>
        </li>
        <li>
          <section>
            <button className="goBtn goExchange" onClick={() => navigate('/exchange')}>
              <span>환전신청 하러 가기</span>
              <IoIosArrowForward className="next" />
            </button>
          </section>
          <section>
            <button className="goBtn makeNew" onClick={() => navigate('/trips/setup')}>
              <span>새 프로젝트 생성하기</span>
              <IoIosArrowForward className="next yellow" />
            </button>
          </section>
        </li>
      </ul>
    </>
  );
}
