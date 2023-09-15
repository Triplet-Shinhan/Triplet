import { useEffect, useRef, useState } from 'react';
import './ExpendModal.scss';
import { useMutation } from '@tanstack/react-query';
import { uploadPayment } from '../../../api/DiaryApis';

export const ExpendModal = ({ setModalOpen, tripId, dailyId }) => {
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
    item: '',
    cost: '',
    foreignCurrency: '',
    date: '',
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(payment);
    updatePayment.mutate({ payment, tripId, dailyId });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPayment((pay) => ({ ...pay, [name]: value }));
  };

  const updatePayment = useMutation(
    ({ payment, tripId, dailyId }) =>
      uploadPayment({ payment, tripId, dailyId }),
    {
      onSuccess: (data) => {
        alert('지출내역 추가 완료');
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
      <form
        method="POST"
        action=""
        className="expendMain"
        onSubmit={handleSubmit}
      >
        <label className="expendLabel" htmlFor="expendTime">
          지출 시간
        </label>
        <input
          className="expendInput"
          type="time"
          id="expendTime"
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
          value={payment.cost}
          onChange={handleChange}
        />
        <label className="expendLabel">지출 방법</label>
        <div className="cashOpt">현금</div>
        <button className="addBtn">추가</button>
      </form>
    </section>
  );
};
