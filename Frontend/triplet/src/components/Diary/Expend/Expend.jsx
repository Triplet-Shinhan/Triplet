import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { deletePayment } from '../../../api/DiaryApis';
import { ExpendModifyModal } from '../DiaryModal/ExpendModifyModal';
import { useState } from 'react';

export default function Expend({ expendInfo, tripId, dailyId }) {
  const { item, cost, date, method, paymentId } = expendInfo.expend;
  const sum = expendInfo.dailyInfo.sum;

  const [expendModifyModalOpen, setExpendModifyModalOpen] = useState(false);
  const showExpendModifyModal = () => setExpendModifyModalOpen(true);

  const handleDelete = () => {
    removePayment.mutate({ paymentId });
  };

  const removePayment = useMutation(({ paymentId }) =>
    deletePayment({ paymentId })
  );
  return (
    <section onClick={showExpendModifyModal}>
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
      <div>
        {expendModifyModalOpen && (
          <ExpendModifyModal
            setModalOpen={setExpendModifyModalOpen}
            paymentId={paymentId}
            tripId={tripId}
            dailyId={dailyId}
            expendItem={item}
            expendCost={cost}
            expendDate={date}
          />
        )}
      </div>
    </section>
  );
}
