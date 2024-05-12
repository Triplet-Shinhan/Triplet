import './DiaryDetail.scss';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import { ImgModal } from '../../Diary/DiaryModal/ImgModal';
import { getCookie } from '../../../api/cookie';
import { logoutUser } from '../../../api/BankAccountApis';
import { useMutation } from '@tanstack/react-query';
import Expend from '../Expend/Expend';
import { ExpendModal } from '../DiaryModal/ExpendModal';

export default function DiaryDetail() {
  const { diary } = useDiaryApi();
  const { tripId, dailyId } = useParams();
  const dailyInfo = useLocation().state[0]; // dailies 하나의 정보가 들어있음
  const dayNum = useLocation().state[1];
  const dayImage = useLocation().state[2];

  const day = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const dateInfo = dailyInfo.date !== undefined ? dailyInfo.date.substr(5).split('-').join('.') : '';
  const weekInfo = day[new Date(dailyInfo.date).getDay()];
  const [tempImg, setTempImg] = useState('');

  // 이미지 모달, 지출 내역 모달
  const [imgModalOpen, setImgModalOpen] = useState(false);
  const showImgModal = () => setImgModalOpen(true);
  const [expendModalOpen, setExpendModalOpen] = useState(false);
  const showExpendModal = () => setExpendModalOpen(true);
  let [isChecked, setChecked] = useState(false);

  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));
  const navigate = useNavigate();

  const userLogout = useMutation(() => logoutUser(), {
    onSuccess: () => {
      sessionStorage.removeItem('isLoggedIn');
      navigate('/');
    },
    onError: () => {
      console.log(error);
    },
  });

  const {
    isLoading,
    error,
    data: expendList,
  } = useQuery(['diaryDetail'], () => diary.getExpendList({ tripId, dailyId }), {
    staleTime: 1000 * 6 * 5,
    refetchInterval: 5000,
  });

  const handleExpendDataChange = (newData) => {
    console.log('Expend데이터 변경 감지', newData);
  };
  // 3개 콤마
  const makedot = (text) => text.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');

  useEffect(() => {
    if (!isChecked) {
      if (dayImage === undefined) {
        setTempImg('');
      } else setTempImg(dayImage);
    }
  }, [isChecked, isLoading, expendList, dailyInfo, dayImage]);

  return (
    dailyInfo && (
      <div className="diaryDetailPage">
        <header className="detailHeader">
          <nav className="detailName">Triplet</nav>
        </header>
        <main className="detailMain">
          <section className="expendSec">
            <section className="loginSec">
              <div className="userName">{userName}님</div>
              <button className="logout" onClick={() => userLogout.mutate()}>
                로그아웃
              </button>
            </section>
            <section className="viewSec">
              <div className="viewDate">
                <div className="viewStart">DAY{dayNum}</div>
                <div className="viewDay">{dateInfo}</div>
                <div>{weekInfo}</div>
              </div>
              <div>₩{makedot(dailyInfo.sum)}</div>
            </section>
            <section className="eachSec">
              {expendList !== undefined
                ? expendList.map((expend, index) => (
                    <Expend
                      key={index}
                      expendInfo={expend}
                      sum={dailyInfo.sum}
                      tripId={tripId}
                      dailyId={dailyId}
                      onDataChange={handleExpendDataChange}
                    />
                  ))
                : ''}
            </section>
            <button className="addModify" onClick={showExpendModal}>
              +
            </button>
          </section>
          <section className="imgSec">
            {tempImg === '' ? (
              <div className="noImage">업로드된 이미지가 없습니다.</div>
            ) : (
              <img className="imgSrc" src={tempImg} alt="이미지" />
            )}
            <button className="modifyBtn" onClick={showImgModal}>
              <img className="modifyImg" src="../../../assets/icons/modify.png" alt="수정버튼" />
            </button>
          </section>

          <div>
            {imgModalOpen && (
              <ImgModal
                setModalOpen={setImgModalOpen}
                tripId={tripId}
                dailyId={dailyId}
                onImageUpload={(imageData) => {
                  setChecked(true);
                  setTempImg(imageData.data.url);
                }}
              />
            )}
          </div>
          <div>
            {expendModalOpen && <ExpendModal setModalOpen={setExpendModalOpen} tripId={tripId} dailyId={dailyId} />}
          </div>
        </main>
      </div>
    )
  );
}
