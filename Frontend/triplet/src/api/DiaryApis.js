import axios from 'axios';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: process.env.REACT_APP_TRIPLET_SERVER_IP,
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }
}

export const makeNewTrip = ({ tripInfo }) => {
  return axios.post(
    process.env.REACT_APP_TRIPLET_SERVER_IP + 'api/trips',
    { data: tripInfo },
    { headers: { 'Content-type': 'application/json' } }
  );
};
