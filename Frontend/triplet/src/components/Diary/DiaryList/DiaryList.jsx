import { useMemo } from 'react';
import getDiaryDate from '../../../utils/getDiaryDate';
import DiaryFractal from '../DiaryFractal/DiaryFractal';

const DiaryList = ({ spaceDate, tripInfo, tripId, tripStart, tripData }) => {
  const fractals = useMemo(() => {
    let dayNum = 0;
    return Array.from({ length: spaceDate + 1 }, (_, index) => {
      const diaryDateInfo = getDiaryDate(tripInfo.startDate, tripInfo.endDate, tripStart, index);
      const isValid = diaryDateInfo.isValid;
      const image = isValid && tripData.dailies[dayNum]?.imageUrl;
      const dailyInfo = isValid && tripData.dailies[dayNum++];

      return (
        <DiaryFractal
          key={diaryDateInfo.date}
          date={diaryDateInfo.date}
          tripId={tripId}
          isValid={isValid}
          day={index + 1}
          image={image}
          dailyInfo={dailyInfo}
        />
      );
    });
  }, [spaceDate, tripInfo, tripId, tripStart, tripData]);

  return <ul className="calSelection">{fractals}</ul>;
};

export default DiaryList;
