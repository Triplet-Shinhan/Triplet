import axios from 'axios';

const PROXY = window.location.hostname === 'localhost' ? '' : '/proxy';

export const AxiosInstance = axios.create({
  withCredentials: true,
  baseURL: process.env.REACT_APP_TRIPLET_SERVER_IP,
});

export const checkAccount = ({ name, accountNum }) => {
  return axios.post(`${PROXY}/v1/auth/1transfer`, {
    name,
    accountNum,
  });
};

export const signupUser = ({ formData }) => {
  return axios.post(
    process.env.REACT_APP_TRIPLET_SERVER_IP + '/users/signup',
    formData,
    { headers: { 'Content-type': 'application/json; charset=UTF-8' } }
  );
};
