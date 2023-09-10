import React, { useState } from 'react';

export default function Exchange() {
  const [exchangeForm, setExchangeForm] = useState({
    exchangeRate: 0,
    expectedMoney: 0,
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
    <>
      <header>
        <nav>환전</nav>
      </header>
      <main>
        <form action="POST">
          <section>
            <span>안녕하세요</span>
            <span>신한 해커톤님</span>
          </section>
          <section className="exRate">
            <label htmlFor="exRate">신한 해커톤님의 우대율</label>
            <input
              type="number"
              id="exRate"
              name="exchangeRate"
              value={exchangeForm.exchangeRate}
              onChange={handleChange}
            />
          </section>
          <section className="exMoney">
            <div>환전금액</div>
            <select>
              <option></option>
            </select>
            <input
              type="number"
              placeholder="수기 입력"
              value={exchangeForm.expectedMoney}
              name="expectedMoney"
              onChange={handleChange}
              required
            />
            <div>=</div>
            <span>KWD</span>
            {/* 변환한 값 들어갈거 밑에 얘 대신에 */}
            <span>{exchangeForm.expectedMoney}</span>
          </section>
          <section className="personalInfo">
            <section className="pickPlace">
              <div>외화수령 영업점 선택</div>
            </section>
            <section className="howToGet">
              <div>수령방법</div>
              {/* radio 버튼?, 택 1 */}
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
                value={exchangeForm.phoneNum}
                onChange={handleChange}
              />
            </section>
          </section>
        </form>
      </main>
    </>
  );
}
