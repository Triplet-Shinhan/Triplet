import React from 'react';
import './DiaryFractal.scss';
import { useNavigate } from 'react-router-dom';
import makeDots from '../../../utils/makeDots';

// 다이어리 조각 작은거
const DiaryFractal = React.memo(({ date, tripId, isValid, day, image, dailyInfo }) => {
  const navigate = useNavigate();

  return (
    <li
      className={`diaryFractal ${isValid ? '' : 'blocked'}`}
      onClick={() =>
        navigate(`/trips/${tripId}/dailies/${dailyInfo.dailyId}`, {
          state: [dailyInfo, day, image],
        })
      }
    >
      <div className="tripDate">{date}</div>
      {console.log(dailyInfo)}
      {image && <img className="thumbnail" src={image} alt="thumbnail" />}
      <div className="totalSum">{dailyInfo && dailyInfo.sum !== 0 ? '₩' + makeDots(dailyInfo.sum + '') : ''}</div>
    </li>
  );
});

export default DiaryFractal;
