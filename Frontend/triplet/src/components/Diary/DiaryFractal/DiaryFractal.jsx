import React from 'react';
import './DiaryFractal.scss';
import { useNavigate } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { useDiaryApi } from '../../../context/DiaryApiContext';

// 다이어리 조각 작은거
export default function DiaryFractal({ date, tripId, isValid, dailyInfo }) {
  const navigate = useNavigate();

  return (
    <li
      className={`diaryFractal ${isValid ? '' : 'blocked'}`}
      onClick={() =>
        navigate(`/trips/${tripId}/dailies/${dailyInfo.dailyId}`, {
          state: dailyInfo,
        })
      }
    >
      <div className="tripDate">{date}</div>
      {/* 지출 내역 있으면, 즉 보여줄거면 사진 크기 작게 하고 밑에 지출내역 보여주고, 아니면 이미지 크게 보여줄 것 */}
      <img src="" />
    </li>
  );
}
