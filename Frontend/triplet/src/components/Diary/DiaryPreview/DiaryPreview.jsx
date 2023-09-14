import React from 'react';
import './DiaryPreview.scss';
import { useNavigate } from 'react-router-dom';

export default function DiaryPreview({ diaryInfo }) {
  const navigate = useNavigate();

  console.log(diaryInfo);
  return (
    <ul
      className="diaryPreview"
      onClick={() =>
        navigate(`/trips/${diaryInfo.diaryId}/dailies`, { state: diaryInfo })
      }
    >
      <section className="diaryInfo">
        <li className="tripLoca">{diaryInfo.location}</li>
        <li className="tripName">{diaryInfo.prjName}</li>
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
