import React, { useState } from 'react';
import './Exchange.scss';

export default function Exchange() {
  const budget = [
    'USD',
    'JPY',
    'EUR',
    'CNY',
    'HKD',
    'THB',
    'AUD',
    'CAD',
    'GBP',
    'SGD',
    'TWD',
    'CHF',
    'MYR',
    'PHP',
    'VND',
    'NZD',
    'IDR',
    'INR',
    'AED',
  ];
  const [exchangeForm, setExchangeForm] = useState({
    exchangeRate: '',
    expectedMoney: '',
    getDate: '',
    phoneNum: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setExchangeForm((info) => ({ ...info, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className="exchangePage">
      <header className="exchangeHeader">
        <nav className="exchangeName">환전</nav>
      </header>
      <main>
        <form action="POST" className="exchangeForm">
          <section className="accountInfo">
            <section className="intro">
              <span>안녕하세요</span>
              <span>신한 해커톤님</span>
              <img src="../../../assets/icons/sol.png" alt="sol logo" />
            </section>
            <section className="exRate">
              <label htmlFor="exRate">신한 해커톤님의 우대율</label>
              <input
                type="text"
                id="exRate"
                name="exchangeRate"
                className="exchangeRate"
                placeholder="0"
                value={exchangeForm.exchangeRate}
                onChange={handleChange}
              />
            </section>
            <section className="exMoney">
              <label>환전금액</label>
              <section className="exOption">
                <select>
                  {budget.map((v) => (
                    <option>{v}</option>
                  ))}
                </select>
                <input
                  type="number"
                  placeholder="수기 입력"
                  value={exchangeForm.expectedMoney}
                  name="expectedMoney"
                  className="expectedMoney"
                  onChange={handleChange}
                  required
                />
              </section>
              <div className="same">=</div>
              <section className="kwdChange">
                <span className="koreaMoney">KWD</span>
                {/* 변환한 값 들어갈거 밑에 얘 대신에 */}
                <span className="changedMoney">
                  {exchangeForm.expectedMoney === ''
                    ? '원화 예상 금액'
                    : exchangeForm.exchangeMoney}
                </span>
              </section>
            </section>
          </section>
          <section className="personalInfo">
            <section className="pickPlace">
              <div>외화수령 영업점 선택</div>
            </section>
            <section className="howToGet">
              <div>수령방법</div>
              <label htmlFor="atm" className="visited">
                ATM
                <input type="radio" id="atm" name="way" />
              </label>
              <label htmlFor="visited" className="visited">
                영업점 방문
                <input type="radio" id="visited" name="way" />
              </label>
            </section>
            <section className="getDate">
              <label htmlFor="getDate">수령일</label>
              <input
                type="date"
                id="getDate"
                name="getDate"
                value={exchangeForm.getDate}
                onChange={handleChange}
              />
            </section>
            <section className="phoneNum">
              <label htmlFor="phoneNum">전화번호</label>
              <input
                type="text"
                id="phoneNum"
                name="phoneNum"
                placeholder="010-0000-0000"
                value={exchangeForm.phoneNum}
                onChange={handleChange}
              />
            </section>
          </section>
        </form>
      </main>
    </div>
  );
}
