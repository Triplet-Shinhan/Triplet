import React from 'react';
import './DiaryFractal.scss';
import { useNavigate } from 'react-router-dom';

// 다이어리 조각 작은거
export default function DiaryFractal({
  date,
  tripId,
  isValid,
  day,
  dailyInfo,
}) {
  const navigate = useNavigate();
  console.log(dailyInfo);
  // 3개 콤마
  const makedot = (text) =>
    text.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');

  return (
    <li
      className={`diaryFractal ${isValid ? '' : 'blocked'}`}
      onClick={() =>
        navigate(`/trips/${tripId}/dailies/${dailyInfo.dailyId}`, {
          state: [dailyInfo, day],
        })
      }
    >
      <div className="tripDate">{date}</div>
      {dailyInfo.imageUrl && (
        <img className="thumbnail" src={dailyInfo.imageUrl} alt="thumbnail" />
      )}
      <div className="totalSum">
        {dailyInfo.sum && dailyInfo.sum !== 0
          ? '₩' + makedot(dailyInfo.sum + '')
          : ''}
      </div>
    </li>
  );
}
