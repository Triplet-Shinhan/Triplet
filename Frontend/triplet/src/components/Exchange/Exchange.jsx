import React, { useEffect, useState } from "react";
import { getCookie } from "../../api/cookie";
import { useExchangeApi } from "../../context/ExchangeApiContext";
import { useQuery } from "@tanstack/react-query";
import "./Exchange.scss";

export default function Exchange() {
  const budget = [
    "USD",
    "JPY",
    "EUR",
    "CNY",
    "HKD",
    "THB",
    "AUD",
    "CAD",
    "GBP",
    "SGD",
    "TWD",
    "CHF",
    "MYR",
    "PHP",
    "VND",
    "NZD",
    "IDR",
    "INR",
    "AED",
  ];

  const [exchangeForm, setExchangeForm] = useState({
    currency: "USD",
    amount: "",
    receiptDate: "",
    receiveWay: "",
  });

  // userName 가져오기
  const userName = decodeURI(getCookie("name"));
  const { exchange } = useExchangeApi();

  // 서버에서 환전 메인화면 가져오기
  const {
    isLoading,
    error,
    data: rateData,
  } = useQuery(
    ["ExchangeMain"],
    () => {
      const v = exchange.viewExchangeMain();
      console.log(v);
      return v;
    },
    {
      staleTime: 1000 * 6 * 5,
    }
  );

  const {
    isloaingPlace,
    errorPlace,
    data: locations,
  } = useQuery(["ExchangePlace"], () => exchange.viewNearLocations(), {
    staleTime: 1000 * 6 * 5,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setExchangeForm((info) => ({ ...info, [name]: value }));
    console.log(exchangeForm);
  };

  useEffect(() => {}, [rateData]);

  //우대율
  const [preferentialRate, setPrefRate] = useState(0.0);
  const onChangeHandler = (e) => {
    setPrefRate(e.currentTarget.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  if (isLoading) {
    return <div>loading...</div>;
  }

  return (
    rateData !== undefined && (
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
                <div>신한 해커톤님의 우대율</div>
                <div>{preferentialRate}</div>
              </section>
              <section className="exMoney">
                <label htmlFor="currency">환전금액</label>
                <section className="exOption">
                  <select
                    value={exchangeForm.currency}
                    onChange={onChangeHandler}
                    name="currency"
                    id="currency"
                    required
                  >
                    {rateData.dataBody.exchangeData.map((v, i) => (
                      <option value={v.preferentialRate}>{v.currencyCode}</option>
                    ))}
                  </select>
                  <input
                    type="number"
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
                  <span className="koreaMoney">KWD</span>
                  {/* 변환한 값 들어갈거 밑에 얘 대신에 */}
                  <span className="changedMoney">
                    {exchangeForm.amount === "" ? "원화 예상 금액" : exchangeForm.exchangeMoney}
                  </span>
                </section>
              </section>
            </section>
            <section className="personalInfo">
              <section className="pickPlace">
                <div>외화수령 영업점 선택</div>
              </section>
              <section className="howToGet" value={exchangeForm.receiveWay} onChange={handleChange}>
                <div>수령방법</div>
                <label htmlFor="atm" className="visited Atm">
                  ATM
                  <input type="radio" id="atm" name="receiveWay" value="1" />
                </label>
                <label htmlFor="visited" className="visited">
                  영업점 방문
                  <input type="radio" id="visited" name="receiveWay" value="2" />
                </label>
              </section>
              <section className="getDate">
                <label htmlFor="receiptDate">수령일</label>
                <input
                  type="date"
                  id="receiptDate"
                  name="receiptDate"
                  value={exchangeForm.receiptDate}
                  onChange={handleChange}
                />
              </section>
            </section>
            <button>제출</button>
          </form>
        </main>
      </div>
    )
  );
}
