import React, { useEffect, useState } from 'react';
import { getCookie } from '../../api/cookie';
import { useExchangeApi } from '../../context/ExchangeApiContext';
import { useMutation, useQuery } from '@tanstack/react-query';
import { applyExchange } from '../../api/ExchangeApis';
import useGeolocation from 'react-hook-geolocation';
import './Exchange.scss';
import Loading from '../Loading/Loading';

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
    currency: 'USD',
    amount: '',
    receiptDate: '',
    receiveWay: '',
    location: '',
  });

  const [rateInfo, setRateInfo] = useState({
    preferentialRate: '90',
    exchangeRate: '',
  });

  // userName 가져오기
  const userName = decodeURI(getCookie('name'));
  const { exchange } = useExchangeApi();
  const geolocation = useGeolocation();

  // 환율 저장해놓기

  // 서버에서 환전 메인화면 가져오기
  const {
    isLoading,
    error,
    data: rateData,
  } = useQuery(
    ['ExchangeMain'],
    () => {
      const v = exchange.viewExchangeMain();
      return v;
    },
    {
      staleTime: 1000 * 6 * 5,
    }
  );

  const {
    isloadingPlace,
    errorPlace,
    data: locations,
  } = useQuery(
    ['ExchangePlace'],
    () => exchange.viewNearLocations(geolocation, exchangeForm.currency),
    {
      staleTime: 1000 * 6 * 5,
    }
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'currency') {
      // 화폐가 변경되면 해당 화폐의 환율을 preferentialRate로 설정
      const index = budget.findIndex((bud) => bud === value);
      if (index !== -1) {
        const newPreferentialRate =
          rateData.dataBody.exchangeData[index].preferentialRate;
        const newExchangeRate =
          rateData.dataBody.exchangeData[index].exchangeRate;
        setRateInfo((info) => ({
          ...info,
          preferentialRate: newPreferentialRate,
          exchangeRate: newExchangeRate,
        }));
      }
      setExchangeForm((info) => ({ ...info, [name]: value }));
    } else {
      setExchangeForm((info) => ({ ...info, [name]: value }));
    }
  };

  const getReceiveWayClass = (value) => {
    return exchangeForm.receiveWay === value ? 'selected' : '';
  };

  // 환전 조회
  const viewExchange = useMutation(
    ({ exchangeForm }) => applyExchange({ exchangeForm }),
    {
      onSuccess: (data) => {
        console.log(data);
      },
      onError: (error) => {
        console.log(error);
      },
    }
  );

  useEffect(() => {
    console.log(locations);
  }, [rateData, locations]);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(`exchangeForm `);
    console.log(exchangeForm);
    viewExchange.mutate();
  };

  if (isLoading || isloadingPlace) {
    return <Loading />;
  }

  return (
    <div className="exchangePage">
      <header className="exchangeHeader">
        <nav className="exchangeName">환전</nav>
      </header>
      <main>
        <form action="POST" className="exchangeForm" onSubmit={handleSubmit}>
          <section className="accountInfo">
            <section className="intro">
              <span className="hello">안녕하세요</span>
              <span>{userName}님</span>
              <img src="../../../assets/icons/sol.png" alt="sol logo" />
            </section>
            <section className="exRate">
              <div>{userName}님의 우대율</div>
              <div className="pfRate">{rateInfo.preferentialRate}%</div>
            </section>
            <section className="exMoney">
              <label htmlFor="currency">환전금액</label>
              <section className="exOption">
                <select
                  name="currency"
                  id="currency"
                  value={exchangeForm.currency}
                  onChange={handleChange}
                  required
                >
                  {rateData.dataBody.exchangeData.map((v, i) => (
                    <option key={i} value={v.currencyCode}>
                      {v.currencyCode}
                    </option>
                  ))}
                </select>
                <input
                  type="text"
                  placeholder="수기 입력"
                  value={exchangeForm.amount}
                  name="amount"
                  className="expectedMoney"
                  onChange={handleChange}
                  required
                />
              </section>
              <div className="same">=</div>
              <section className="kwdChange">
                <span className="koreaMoney">KRW</span>
                {/* 변환한 값 들어갈거 밑에 얘 대신에 */}
                <span className="changedMoney">
                  {exchangeForm.amount === ''
                    ? '원화 예상 금액'
                    : exchangeForm.amount *
                      rateInfo.preferentialRate *
                      rateInfo.exchangeRate}
                </span>
              </section>
            </section>
          </section>
          <section className="personalInfo">
            <section className="pickPlace">
              <div>외화수령 영업점 선택</div>
              <select
                className="locations"
                name="location"
                value={exchangeForm.location}
                onChange={handleChange}
                required
              >
                {locations &&
                  locations.dataList.map((v) => (
                    <option className="locaBtn">
                      {v.branchName}
                      {v.address}
                    </option>
                  ))}
              </select>
            </section>
            <section
              className="howToGet"
              value={exchangeForm.receiveWay}
              onChange={handleChange}
              required
            >
              <div>수령방법</div>
              <label
                htmlFor="atm"
                className={`visited Atm ${getReceiveWayClass('1')}`}
              >
                ATM
                <input type="radio" id="atm" name="receiveWay" value="1" />
              </label>
              <label
                htmlFor="visited"
                className={`visited ${getReceiveWayClass('2')}`}
              >
                영업점 방문
                <input type="radio" id="visited" name="receiveWay" value="2" />
              </label>
            </section>
            <section className="getDate">
              <label htmlFor="receiptDate">수령일</label>
              <input
                className="receiptDate"
                type="date"
                id="receiptDate"
                name="receiptDate"
                value={exchangeForm.receiptDate}
                onChange={handleChange}
                required
              />
            </section>
            <section className="exchangeSec">
              <button className="exchangeBtn">제출</button>
            </section>
          </section>
        </form>
      </main>
    </div>
  );
}
