import { useMutation } from '@tanstack/react-query';
import { useEffect } from 'react';
import React from 'react';
import { deletePayment } from '../../../api/DiaryApis';
import { ExpendModifyModal } from '../DiaryModal/ExpendModifyModal';
import './Expend.scss';
import { useState } from 'react';

export default function Expend({ expendInfo, tripId, dailyId, onDataChange }) {
  console.log(expendInfo);
  const { item, cost, date, method, paymentId } = expendInfo;

  const [expendModifyModalOpen, setExpendModifyModalOpen] = useState(false);
  const showExpendModifyModal = () => setExpendModifyModalOpen(true);

  const handleDelete = () => {
    removePayment.mutate({ paymentId });
  };

  const removePayment = useMutation(({ paymentId }) =>
    deletePayment({ paymentId })
  );

  useEffect(() => {
    onDataChange(expendInfo);
  }, [expendInfo, onDataChange]);

  // 3개 콤마
  const makedot = (text) =>
    text.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ',');

  return (
    <section className="expendForm">
      <section className="timeInfo">
        <div>{`${date.substr(11)}  |  ${method}`}</div>
      </section>
      <section className="placeInfo">
        <div>{item}</div>
        <div className="placeCost" onClick={showExpendModifyModal}>
          ₩{makedot(cost)}
        </div>
      </section>
      <button className="delBtn" onClick={handleDelete}>
        삭제하기
      </button>
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
      <hr />
    </section>
  );
}
