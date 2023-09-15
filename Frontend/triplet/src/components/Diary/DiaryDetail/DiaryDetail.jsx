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

  return (
    <div className="diaryDetailPage">
      <header className="detailHeader">
        <nav className="detailName">Triplet</nav>
      </header>
      <main className="detailMain">
        <section className="expendSec">
          <section className="loginSec"></section>
          <section className="viewSec">
            <div className="viewDate">
              <div className="viewStart">DAY1</div>
              <div className="viewDay">{dateInfo}</div>
              <div>{weekInfo}</div>
            </div>
            <div>{dailyInfo.sum}</div>
          </section>
          <section className="eachSec">
            {expendList == undefined
              ? ''
              : expendList.map((expend) => <Expend expendInfo={expend} />)}
          </section>
          <button className="addModify" onClick={showExpendModal}>
            +
          </button>
        </section>
        <section className="imgSec">
          <img
            className="imgSrc"
            src="../../../assets/sample.JPG"
            alt="사진이미지"
          />
          <button className="modifyBtn" onClick={showImgModal}>
            <img className="modifyImg" src="../../../assets/icons/modify.png" />
          </button>
        </section>
        <div>
          {imgModalOpen && (
            <ImgModal
              setModalOpen={setImgModalOpen}
              tripId={tripId}
              dailyId={dailyId}
            />
          )}
        </div>
        <div>
          {expendModalOpen && <ExpendModal setModalOpen={setExpendModalOpen} />}
        </div>
      </main>
    </div>
  );
}
