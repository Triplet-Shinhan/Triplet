import React from 'react';
import './DiaryPreview.scss';
import { useNavigate } from 'react-router-dom';

export default function DiaryPreview({ diaryInfo }) {
  const navigate = useNavigate();

  return (
    <ul
      key={diaryInfo.tripId}
      className="diaryPreview"
      onClick={() => navigate('/trips/dailies')}
    >
      <section className="diaryInfo">
        <li className="tripLoca">{diaryInfo.tripLocation}</li>
        <li className="tripName">{diaryInfo.tripPName}</li>
        <li className="tripDate">
          {diaryInfo.startDate} ~ {diaryInfo.endDate}
        </li>
      </section>
      <section className="diaryIcons">
        <img
          className="airplane"
          src="../../../assets/icons/air-transport.png"
        />
      </section>
    </ul>
  );
}
