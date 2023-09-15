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

export const makeNewTrip = ({ tripInfo }) => {
  console.log({ tripInfo });
  return axios.post(`${PROXY}/api/trips`, tripInfo);
};
