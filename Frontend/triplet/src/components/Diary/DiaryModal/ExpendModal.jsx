import { useEffect, useRef, useState } from 'react';
import './ExpendModal.scss';

export const ExpendModal = ({ setModalOpen, tripId, dailyId }) => {
  const [imageSrc, setImageSrc] = useState(null);

  const closeModal = () => {
    setModalOpen(false);
  };

  const [payment, setPayment] = useState({
    item: '',
    cost: '',
    foreignCurrency: '',
    date: '',
  });
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
  return (
    <section className="expendContainer" ref={modalRef}>
      <nav className="expendNav">
        <div className="expendHeader">지출 내역</div>
        <button className="deleteModal" onClick={closeModal}>
          X
        </button>
      </nav>
      <form method="POST" action="" className="expendMain">
        <label className="expendLabel" htmlFor="expendTime">
          지출 시간
        </label>
        <input className="expendInput" type="time" id="expendTime" />
        <label className="expendLabel" htmlFor="expendPlace">
          지출 장소
        </label>
        <input
          className="expendInput"
          type="text"
          id="expendPlace"
          placeholder="장소를 입력해주세요."
        />
        <label className="expendLabel" htmlFor="expendMoney" placeholder="금액">
          지출 금액
        </label>
        <input className="expendInput" type="text" id="expendMoney" />
        <label className="expendLabel">지출 방법</label>
        <div className="cashOpt">현금</div>
        <button className="addBtn">추가</button>
      </form>
    </section>
  );
};
