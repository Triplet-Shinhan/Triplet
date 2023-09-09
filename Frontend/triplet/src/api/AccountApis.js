import axios from 'axios';

export const checkAccount = ({ name, account }) => {
  return axios.post('http://43.201.254.59:8080/check', {
    name,
    account,
  });
};

export const signupUser = ({ formData }) => {
  return axios.post(
    'http://43.201.254.59:8080/users/signup',
    { data: formData },
    { headers: { 'Content-type': 'application/json' } }
  );
};
