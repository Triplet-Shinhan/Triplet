import axios from 'axios';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export default class Exchange {
  constructor() {
    this.httpClient = axios.create({
      baseURL: PROXY,
      withCredentials: true,
    });
  }

  //환전화면 조회
  async viewExchangeMain() {
    return this.httpClient.get('/api/exchanges').then((res) => res.data);
  }

  //가까운 환전 가능 지점 조회
  async viewNearLocations(position, currency) {
    return this.httpClient
      .get(
        `/api/exchanges/locations?latitude=${position.coords.latitude}
        &longitude=
        ${position.coords.longitude}&currency=${currency}`
      )
      .then((res) => res.data);
  }
  // 환전신청
  async applyExchange() {
    return this.httpClient.post('/api/exchanges').then((res) => res.data);
  }
}
