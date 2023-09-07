import React from 'react';

export default function DiaryPreview({ diaryInfo }) {
  const { tripId, tripLocation, tripPName, startDate, endDate } = { diaryInfo };
  return (
    <ul key={tripId}>
      <li>{tripLocation}</li>
      <li>{tripPName}</li>
      <li>
        {startDate} ~ {endDate}
      </li>
    </ul>
  );
}
