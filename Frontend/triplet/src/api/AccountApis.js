import axios from 'axios';

export const checkAccount = ({ name, account }) => {
  return axios.post('http://localhost:8080/check', { name, account });
};

export const signupUser = ({ formData }) => {
  return axios({
    method: 'POST',
    url: 'http://localhost:8080/users/signup',
    data: formData,
    headers: { 'Content-type': 'application/json' },
  });
  // return axios.post(
  //   'http://localhost:8080/users/signup',
  //   { data: formData },
  //   { headers: { 'Content-type': 'application/json' } }
  // );
};
