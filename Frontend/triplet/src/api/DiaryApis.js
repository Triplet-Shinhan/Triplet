import axios from 'axios';

export default class Diary {
  constructor() {
    this.httpClient = axios.create({
      baseURL: 'https://localhost:8080',
    });
  }

  // 프로젝트보기
  async viewProject() {
    return this.httpClient.get('/api/trips').then((res) => res.data);
  }
}
