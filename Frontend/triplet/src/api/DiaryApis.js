import axios from 'axios';
import { AxiosInstance } from '../api/AccountApis';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: AxiosInstance.baseURL,
      withCredentials: true,
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }
}

export const makeNewTrip = ({ tripInfo }) => {
  return axios.post(`${AxiosInstance.baseURL}/api/trips`, tripInfo);
};
