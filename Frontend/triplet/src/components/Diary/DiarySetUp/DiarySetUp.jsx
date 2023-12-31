import React, { useState } from 'react';
import './DiarySetUp.scss';
import { getCookie } from '../../../api/cookie';
import { useMutation } from '@tanstack/react-query';
import { makeNewTrip } from '../../../api/DiaryApis';
import { useNavigate } from 'react-router-dom';

export default function DiarySetUp() {
  const budget = [
    '미국달러(USD)',
    '일본엔(JPY)',
    '유럽유로(EUR)',
    '중국위안(CNY)',
    '홍콩달러(HKD)',
    '태국바트(THB)',
    '호주달러(AUD)',
    '캐나다달러(CAD)',
    '영국파운드(GBP)',
    '싱가폴달러(SGD)',
    '대만달러(TWD)',
    '스위스프랑(CHF)',
    '말레이시아링기트(MYR)',
    '필리핀페소(PHP)',
    '베트남동(VND)',
    '뉴질랜드달러(NZD)',
    '인도네시아루피아(IDR)',
    '인도루피(INR)',
    '아랍에밀리트 디르함(AED)',
  ];

  const [tripInfo, setTripInfo] = useState({
    prjName: '',
    location: '',
    currency: '미국달러(USD)',
    budget: '',
    startDate: '',
    endDate: '',
    exchangedBudget: '',
  });

  // 쿠키 가져오기
  const userName = decodeURI(getCookie('name'));
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTripInfo((trip) => ({ ...trip, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    makeTripDiary.mutate();
  };

  // 프로젝트 정보 넘기기
  const makeTripDiary = useMutation(() => makeNewTrip({ tripInfo }), {
    onSuccess: (data) => {
      alert('생성');
      navigate(`/trips/${data.data.tripId}/dailies`, { state: tripInfo });
    },
    onError: () => {
      console.log('생성이 되지 않았습니다.');
    },
  });

  return (
    <>
      <main className="diarySetupMain">
        <h1 className="diaryH1">안녕하세요</h1>
        <h2 className="diaryH2">{userName}님</h2>
        <form className="diaryForm" action="POST" onSubmit={handleSubmit}>
          <figure className="logoArea">
            <img
              className="logo"
              src="../../../assets/icons/shinhan-symbol.png"
              alt="logo"
            />
            <figcaption>Triplet</figcaption>
          </figure>
          <section>
            <label htmlFor="prjName">프로젝트 이름을 설정해주세요</label>
            <input
              type="text"
              id="prjName"
              name="prjName"
              value={tripInfo.prjName}
              onChange={handleChange}
              placeholder="프로젝트 이름"
              required
            />
          </section>
          <section>
            <label htmlFor="currency">통화설정을 해주세요</label>
            <select
              name="currency"
              id="currency"
              value={tripInfo.currency !== '' ? tripInfo.currency : budget[0]}
              onChange={handleChange}
              required
            >
              {budget.map((v, i) => (
                // selected 옵션
                <option key={i} value={v}>
                  {v}
                </option>
              ))}
            </select>
          </section>
          <section>
            <label htmlFor="tripDate" className="tripDate">
              여행 기간을 알려주세요
            </label>
            <input
              type="date"
              id="tripDate"
              name="startDate"
              value={tripInfo.startDate}
              onChange={handleChange}
              required
            />
            <input
              type="date"
              id="tripDate"
              name="endDate"
              min={tripInfo.startDate}
              value={tripInfo.endDate}
              onChange={handleChange}
              required
            />
          </section>
          <section>
            <label htmlFor="location">여행지를 적어주세요</label>
            <input
              type="text"
              id="location"
              name="location"
              placeholder="여행지 이름"
              value={tripInfo.location}
              onChange={handleChange}
              required
            />
          </section>
          <section>
            <label htmlFor="budget">예산을 알려주세요</label>
            <input
              type="text"
              id="budget"
              name="budget"
              placeholder="₩250,000"
              value={tripInfo.budget}
              onChange={handleChange}
              required
            />
          </section>
          <section>
            <label htmlFor="budget">환전 현금</label>
            <input
              type="text"
              id="exchangedBudget"
              name="exchangedBudget"
              placeholder=""
              value={tripInfo.exchangedBudget}
              onChange={handleChange}
              required
            />
          </section>
          <button className="makeDiary">생성하기</button>
          <section className="boardingArea">
            <span className="boardingArea">boarding pass</span>
          </section>
        </form>
        <section className="backgroundForm">
          <section className="backgroundFooter">Boarding Pass</section>
        </section>
        <img
          className="airplaneLogo"
          src="../../../assets/airplane.png"
          alt="airplane"
        />
      </main>
    </>
  );
}
