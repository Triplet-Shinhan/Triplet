import axios from 'axios';
const PROXY = 'http://localhost:3009';
// const PROXY = window.location.hostname === 'localhost' ? 'http://localhost:3010' : '/proxy';

export default class BankAccount {
  constructor() {
    this.httpClient = axios.create({
      baseURL: PROXY,
      withCredentials: true,
    });
  }
}

// 로그인
export const loginUser = ({ email, password }) => {
  return axios.post(`${PROXY}/users`, { email, password });
};

// 1원 계좌
export const checkAccount = ({ name, accountNum }) => {
  return axios.post(`${PROXY}/v1/auth/1transfer`, {
    name,
    accountNum,
  });
};

// 회원 가입
export const signupUser = ({ formData }) => {
  return axios.post(`${PROXY}/users`, formData, {
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
};

// 로그아웃
export const logoutUser = () => {
  console.log(PROXY);
  return axios.post(`${PROXY}/users/logout`);
};

export const makeNewTrip = ({ tripInfo }) => {
  console.log({ tripInfo });
  return axios.post(`${PROXY}/api/trips`, tripInfo);
};
