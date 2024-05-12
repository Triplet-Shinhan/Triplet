import axios from 'axios';
const PROXY = 'http://localhost:3010';
// const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: PROXY,
      // withCredentials: true,
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/trips').then((res) => res.data);
  }

  // 다이어리 생성할 때 넣었던 정보 확인하기
  async viewDiaryDetail({ tripId }) {
    return await this.httpClient.get(`/trips/${tripId}/dailies`).then((res) => res.data);
  }

  // 지출 내역 조회
  async getExpendList({ tripId, dailyId }) {
    return this.httpClient.get(`/trips/${tripId}/dailies/${dailyId}`).then((res) => res.data);
  }
}

// 새 프로젝트 만들기
export const makeNewTrip = (tripInfo) => {
  return axios.post(`${PROXY}/trips`, tripInfo);
};

// 이미지 업로드
export const uploadImage = ({ imageFile, tripId, dailyId }) => {
  const formData = new FormData();
  formData.append('image', imageFile);

  return axios.post(`${PROXY}/trips/${tripId}/dailies/${dailyId}/images`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
};

// 지출 내역 업로드
export const uploadPayment = ({ payment, tripId, dailyId }) => {
  const { item, cost, date } = payment;
  return axios.post(`${PROXY}/payments`, {
    item,
    cost,
    date,
    tripId,
    dailyId,
  });
};

// 지출 내역 수정
export const modifyExpend = ({ tripId, dailyId, paymentId, payment }) => {
  const { item, cost, date } = payment;
  return axios.put(`${PROXY}/payments/${paymentId}`, {
    tripId,
    dailyId,
    item,
    cost,
    date,
  });
};

// 다이어리 날짜 수정
export const modifyDate = ({ tripId, tripDate }) => {
  const { startDate, endDate } = tripDate;
  return axios.patch(`${PROXY}/trips/${tripId}`, { startDate, endDate });
};

// 다이어리 삭제
export const deleteProject = ({ tripId }) => {
  return axios.delete(`${PROXY}/trips/${tripId}`);
};

// 지출 내역 삭제
export const deletePayment = ({ paymentId }) => {
  return axios.delete(`${PROXY}/payments/${paymentId}`);
};
