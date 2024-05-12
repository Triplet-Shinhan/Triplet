// 다이어리 조각 만들기
const getDiaryDate = (startDate, endDate, dateString, start) => {
  let isValid = false;
  const date = new Date(dateString);
  const wantedWeek = new Date(date);
  wantedWeek.setDate(date.getDate() + start);
  const month = String(wantedWeek.getMonth() + 1).padStart(2, '0');
  const day = String(wantedWeek.getDate()).padStart(2, '0');
  isValid =
    wantedWeek.getTime() >= new Date(startDate).getTime() && wantedWeek.getTime() <= new Date(endDate).getTime();
  return { date: `${month}.${day}`, isValid: isValid };
};

export default getDiaryDate;
