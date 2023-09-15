import axios from 'axios';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export default class Account {
  constructor() {
    this.httpClient = axios.create({
      withCredentials: true,
      baseURL: `${PROXY}`,
    });
  }

  async loginUser({ email, password }) {
    return this.httpClient
      .post(`${PROXY}/users/login`, { email, password })
      .then((res) => res.data);
  }
}

// 1원 계좌
export const checkAccount = ({ name, accountNum }) => {
  return axios.post(`${PROXY}/v1/auth/1transfer`, {
    name,
    accountNum,
  });
};

// 회원 가입
export const signupUser = ({ formData }) => {
  return axios.post(`${PROXY}/users/signup`, formData, {
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
};

export const logoutUser = () => {
  return axios.post(`${PROXY}/users/logout`, {});
};
