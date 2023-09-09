import axios from 'axios';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: 'http://43.201.254.59:8080/',
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }
}

export const makeNewTrip = ({ tripInfo }) => {
  return axios.post(
    'http://43.201.254.59:8080/api/trips',
    { data: tripInfo },
    { headers: { 'Content-type': 'application/json' } }
  );
};
