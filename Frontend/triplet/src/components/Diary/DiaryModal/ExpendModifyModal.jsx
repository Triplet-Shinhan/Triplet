import { useEffect, useRef, useState } from 'react';
import './ExpendModal.scss';
import { useMutation } from '@tanstack/react-query';
import { modifyExpend, uploadPayment } from '../../../api/DiaryApis';

export const ExpendModifyModal = ({
  setModalOpen,
  paymentId,
  tripId,
  dailyId,
  expendItem,
  expendCost,
  expendDate,
}) => {
  const closeModal = () => {
    setModalOpen(false);
  };

  const modalRef = useRef(null);

  useEffect(() => {
    const handler = (e) => {
      if (modalRef.current && !modalRef.current.contains(e.target)) {
        setModalOpen(false);
      }
    };

    document.addEventListener('mousedown', handler);

    return () => {
      document.removeEventListener('mousedown', handler);
    };
  });

  const [payment, setPayment] = useState({
    item: expendItem,
    cost: expendCost,
    date: expendDate,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(payment);
    closeModal();
    updatePayment.mutate({ tripId, dailyId, paymentId, payment });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPayment((pay) => ({ ...pay, [name]: value }));
  };

  const updatePayment = useMutation(
    ({ tripId, dailyId, paymentId, payment }) =>
      modifyExpend({ tripId, dailyId, paymentId, payment }),
    {
      onSuccess: () => {
        alert('수정 완료');
      },
      onError: (error) => {
        console.log(error);
      },
    }
  );

  return (
    <section className="expendContainer" ref={modalRef}>
      <nav className="expendNav">
        <div className="expendHeader">지출 내역</div>
        <button className="deleteModal" onClick={closeModal}>
          X
        </button>
      </nav>
      <form method="POST" className="expendMain" onSubmit={handleSubmit}>
        <label className="expendLabel" htmlFor="expendTime">
          지출 시간
        </label>
        <input
          className="expendInput"
          type="time"
          id="expendTime"
          name="date"
          value={payment.date}
          onChange={handleChange}
        />
        <label className="expendLabel" htmlFor="expendPlace">
          지출 장소
        </label>
        <input
          className="expendInput"
          type="text"
          id="expendPlace"
          name="item"
          placeholder="장소를 입력해주세요."
          value={payment.item}
          onChange={handleChange}
        />
        <label className="expendLabel" htmlFor="expendMoney" placeholder="금액">
          지출 금액
        </label>
        <input
          className="expendInput"
          type="text"
          id="expendMoney"
          name="cost"
          value={payment.cost}
          onChange={handleChange}
        />
        <label className="expendLabel">지출 방법</label>
        <div className="cashOpt">현금</div>
        <button className="addBtn">수정</button>
      </form>
    </section>
  );
};
