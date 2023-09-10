import axios from 'axios';
import React from 'react';

export default class BankAccount {
  constructor() {
    this.httpClient = axios.create({
      baseURL: process.env.REACT_APP_TRIPLET_SERVER_IP,
    });
  }

  // 계좌번호 인증 (본인계좌여야 가능하기 때문에 name, account)
  async verifyBankAccount(name, account) {
    return this.httpClient
      .post('', {
        params: {},
      })
      .then((res) => res.data);
  }
}
