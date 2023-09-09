import axios from 'axios';

export const checkAccount = ({ name, account }) => {
  return axios.post('http://54.180.9.17:8080/check', {
    name,
    account,
  });
};

export const signupUser = ({ formData }) => {
  return axios.post(
    'http://54.180.9.17:8080/users/signup',
    { data: formData },
    { headers: { 'Content-type': 'application/json' } }
  );
};
