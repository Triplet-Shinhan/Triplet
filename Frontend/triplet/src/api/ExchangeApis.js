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
  async viewNearLocations(geolocation, currency) {
    const { latitude, longitude } = geolocation;

    return this.httpClient
      .get(
        `/api/exchanges/locations?latitude=${latitude}
        &longitude=
        ${longitude}&currency=${currency}`
      )
      .then((res) => res.data);
  }
}
// 환전신청
export const applyExchange = ({ exchangeForm }) => {
  return axios.post(`${PROXY}/api/exchanges`, exchangeForm);
};
