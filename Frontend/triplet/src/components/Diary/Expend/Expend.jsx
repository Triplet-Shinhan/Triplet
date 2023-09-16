import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { deletePayment } from '../../../api/DiaryApis';

export default function Expend({ expend, sum }) {
  const { item, cost, date, method, paymentId } = expend;

  const handleDelete = () => {
    removePayment.mutate({ paymentId });
  };

  const removePayment = useMutation(({ paymentId }) =>
    deletePayment({ paymentId })
  );
  return (
    <section>
      <section className="timeInfo">
        <div>{date.substr(11)}</div> | <div>{method}</div>
      </section>
      <section className="placeInfo">
        <div>{item}</div>
        <button onClick={handleDelete}>삭제하기</button>
      </section>
      <section className="expendInfo">
        <div>{cost}</div>
        <div>{sum}</div>
      </section>
    </section>
  );
}
