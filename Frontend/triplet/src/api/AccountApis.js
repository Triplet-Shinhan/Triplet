import axios from 'axios';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export const AxiosInstance = axios.create({
  withCredentials: true,
  baseURL: `${PROXY}`,
});

export const checkAccount = ({ name, accountNum }) => {
  return axios.post(`${PROXY}/v1/auth/1transfer`, {
    name,
    accountNum,
  });
};

export const signupUser = ({ formData }) => {
  return axios.post(`${PROXY}/users/signup`, formData, {
    headers: { 'Content-type': 'application/json; charset=UTF-8' },
  });
};

export const logoutUser = () => {
  return axios.post(`${PROXY}/users/logout`, {});
};
