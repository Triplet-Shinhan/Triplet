import axios from 'axios';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: PROXY,
      withCredentials: true,
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }

  // 다이어리 생성할 때 넣었던 정보 확인하기
  async viewDiaryDetail({ tripId }) {
    return this.httpClient
      .get(`/api/trips/${tripId}/dailies`)
      .then((res) => res.data);
  }

  async getExpendList({ tripId, dailyId }) {
    return this.httpClient
      .get(`/api/trips/${tripId}/dailies/${dailyId}`)
      .then((res) => res.data);
  }
}

// 새 프로젝트 만들기
export const makeNewTrip = ({ tripInfo }) => {
  console.log({ tripInfo });
  return axios.post(`${PROXY}/api/trips`, tripInfo);
};

// 이미지 업로드
export const uploadImage = ({ image, tripId, dailyId }) => {
  return axios.post(
    `${PROXY}/api/trips/${tripId}/dailies/${dailyId}/images`,
    image
  );
};

// 지출 내역 업로드
export const uploadPayment = ({ payment, tripId, dailyId }) => {
  return axios.post(`${PROXY}/api/payment`, { payment, tripId, dailyId });
};

// 다이어리 날짜 수정
export const modifyDate = ({ tripId, tripDate }) => {
  const { startDate, endDate } = tripDate;
  return axios.post(`${PROXY}/api/trips/${tripId}`, { startDate, endDate });
};

// 다이어리 삭제
export const deleteProject = ({ tripId }) => {
  return axios.post(`${PROXY}/api/${tripId}`);
};
