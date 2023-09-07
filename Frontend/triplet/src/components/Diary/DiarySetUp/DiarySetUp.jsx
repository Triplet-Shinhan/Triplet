import React from 'react';

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
  return (
    <form>
      <label for="projectName">프로젝트 이름을 설정해주세요</label>
      <input type="text" id="projectName" />

      <label for="tripPlace">여행지를 적어주세요</label>
      <input type="text" id="tripPlace" />
      {/* 정해져있나 통화가? */}
      <label for="currency">통화설정을 해주세요</label>
      <input type="" id="currency" />
      <label for="budget">예산을 알려주세요</label>
      <select name="budget" id="budget">
        {budget.map((v) => (
          <option value={v}>{v}</option>
        ))}
      </select>
      <label>여행 기간을 알려주세요</label>
    </form>
  );
}
