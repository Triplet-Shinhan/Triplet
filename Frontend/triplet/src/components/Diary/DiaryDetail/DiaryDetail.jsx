import React, { useState } from 'react';
import './DiaryDetail.scss';
import { useLocation, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import { ImgModal } from '../../Diary/DiaryModal/ImgModal';
import Expend from '../Expend/Expend';
import { ExpendModal } from '../DiaryModal/ExpendModal';

export default function DiaryDetail() {
  const { diary } = useDiaryApi();
  const { tripId, dailyId } = useParams();
  const dailyInfo = useLocation().state; // dailies 하나의 정보가 들어있음
  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const dateInfo = dailyInfo.date.substr(5).split('-').join('.');
  const weekInfo = day[new Date(dailyInfo.date).getDay()];

  // 이미지 모달, 지출 내역 모달
  const [imgModalOpen, setImgModalOpen] = useState(false);
  const showImgModal = () => setImgModalOpen(true);
  const [expendModalOpen, setExpendModalOpen] = useState(false);
  const showExpendModal = () => setExpendModalOpen(true);

  const {
    isLoading,
    error,
    data: expendList,
  } = useQuery(
    ['diaryDetail'],
    () => diary.getExpendList({ tripId, dailyId }),
    { staleTime: 1000 * 6 * 5 }
  );

  //   {
  // 		"paymentId": "1",
  // 		"item" : "McDonald",
  // 		"cost" : "150000",
  // 		"foreignCurrency" : "USD",
  // 		"date" : "2023-09-03 12:30:00"
  // },

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
          <section className="eachSec">
            {console.log(expendList)}
            {/* {expendList.map((expend) => (
              <Expend expendInfo={expend} />
            ))} */}
          </section>
          <button onClick={showExpendModal}>+</button>
        </section>
        <section className="imgSec">
          <img
            className="imgSrc"
            src={
              dailyInfo.imageUrl === ''
                ? '../../../assets/icons/sample.JPG'
                : dailyInfo.imageUrl
            }
          />
          <button onClick={showImgModal}>수정</button>
        </section>
        <div>{imgModalOpen && <ImgModal setModalOpen={setImgModalOpen} />}</div>
        <div>
          {expendModalOpen && <ExpendModal setModalOpen={setExpendModalOpen} />}
        </div>
      </main>
    </div>
  );
}
