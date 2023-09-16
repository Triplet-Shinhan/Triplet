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
    </li>
  );
}
