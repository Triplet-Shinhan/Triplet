// 주차 시작 및 끝 날짜 구하기
const getWantedWeek = (dateString, isStart) => {
  const date = new Date(dateString);
  const dayOfWeek = date.getDay();
  const wantedWeek = new Date(date);

  if (isStart) wantedWeek.setDate(date.getDate() - dayOfWeek);
  else wantedWeek.setDate(date.getDate() + (6 - dayOfWeek));

  const year = wantedWeek.getFullYear();
  const month = String(wantedWeek.getMonth() + 1).padStart(2, '0');
  const day = String(wantedWeek.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
};

export default getWantedWeek;
