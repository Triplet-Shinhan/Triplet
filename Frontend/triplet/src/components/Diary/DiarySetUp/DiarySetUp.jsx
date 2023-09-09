import React, { useState } from 'react';
import './DiarySetUp.scss';
import { useMutation } from '@tanstack/react-query';
import { makeNewTrip } from '../../../api/DiaryApis';

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
    tripName: '',
    tripPlace: '',
    tripCurrency: '',
    tripBudget: '',
    startDate: '',
    endDate: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTripInfo((trip) => ({ ...trip, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // startDate 보다 endDate가 빠르면 생성 안되는 로직 추가 필요
    makeTripDiary.mutate({ tripInfo });
  };

  // 프로젝트 정보 넘기기
  const makeTripDiary = useMutation(
    ({ tripInfo }) => makeNewTrip({ tripInfo }),
    {
      onSuccess: () => {
        console.log('success');
        alert('생성');
        // Diary 달력페이지로 이동
      },
      onError: () => {
        console.log('생성이 되지 않았습니다.');
      },
    }
  );

  return (
    <>
      <main className="diarySetupMain">
        <h1>안녕하세요</h1>
        <h2>신한 해커톤님</h2>
        <form className="diaryForm" action="POST" onSubmit={handleSubmit}>
          <figure className="logoArea">
            <img
              className="logo"
              src="../../../assets/icons/shinhan-symbol.png"
              alt="logo"
            />
            <figcaption>Triplet</figcaption>
          </figure>
          <div>
            <label htmlFor="tripName">프로젝트 이름을 설정해주세요</label>
            <input
              type="text"
              id="tripName"
              name="tripName"
              value={tripInfo.tripName}
              onChange={handleChange}
              placeholder="프로젝트 이름"
              required
            />
          </div>
          <div>
            <label htmlFor="tripCurrency">통화설정을 해주세요</label>
            <select
              name="tripCurrency"
              id="tripCurrency"
              value={tripInfo.tripCurrency}
              onChange={handleChange}
              required
            >
              {budget.map((v, i) => (
                <option key={i} value={v}>
                  {v}
                </option>
              ))}
            </select>
          </div>
          <div>
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
              value={tripInfo.endDate}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="tripPlace">여행지를 적어주세요</label>
            <input
              type="text"
              id="tripPlace"
              name="tripPlace"
              placeholder="여행지 이름"
              value={tripInfo.tripPlace}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <label htmlFor="tripBudget">예산을 알려주세요</label>
            <input
              type="text"
              id="tripBudget"
              name="tripBudget"
              placeholder="₩250,000"
              value={tripInfo.tripBudget}
              onChange={handleChange}
              required
            />
          </div>
          <button>
            <div className="makeDiary">생성하기</div>
          </button>
          <div className="boardingArea">boarding pass</div>
        </form>
        <div className="backgroundForm">
          <div className="backgroundFooter">Boarding Pass</div>
        </div>
      </main>
    </>
  );
}
