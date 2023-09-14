import axios from 'axios';
const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export default class Exchange {
  constructor() {
    this.httpClient = axios.create({
      baseURL: PROXY,
      withCredentials: true,
    });
  }
}
