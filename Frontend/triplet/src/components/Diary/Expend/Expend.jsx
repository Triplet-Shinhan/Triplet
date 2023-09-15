import React from 'react';

export default function Expend({ expend }) {
  const { item, cost, date } = expend;
  return (
    <section>
      <section className="timeInfo">
        <div>{date.substr(11)}</div>
      </section>
      <section className="placeInfo">
        <div>{item}</div>
        <button>삭제하기</button>
      </section>
      <section className="expendInfo">
        <div>{cost}</div>
      </section>
    </section>
  );
}
