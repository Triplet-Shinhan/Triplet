import axios from 'axios';
import { AxiosInstance } from '../api/AccountApis';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: process.env.REACT_APP_TRIPLET_SERVER_IP,
      withCredentials: true,
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }
}

export const makeNewTrip = async ({ tripInfo }) => {
  const res = await AxiosInstance.post('/api/trips', tripInfo, {
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
  console.log(res);
};
