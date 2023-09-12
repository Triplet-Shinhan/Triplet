import axios from 'axios';
import { AxiosInstance } from './AccountApis';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';
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
  console.log({ tripInfo });
  return axios.post(`${PROXY}/api/trips`, tripInfo);
};
