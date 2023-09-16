import React, { useState } from 'react';
import './DiarySetting.scss';
import { useMutation } from '@tanstack/react-query';
import { useNavigate, useParams } from 'react-router-dom';
import { modifyDate, deleteProject } from '../../../api/DiaryApis';

export default function DiarySetting() {
  const [tripDate, setTripDate] = useState({ startDate: '', endDate: '' });
  const navigate = useNavigate();
  const { tripId } = useParams();

  const handleDelete = (e) => {
    e.preventDefault();
    deleteDiary.mutate({ tripId });
  };

  const handleChangeDate = (e) => {
    e.preventDefault();
    changeDiaryDate.mutate({ tripId, tripDate });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTripDate((date) => ({ ...date, [name]: value }));
  };

  const deleteDiary = useMutation(({ tripId }) => deleteProject({ tripId }), {
    onSuccess: () => {
      alert('삭제되었습니다.');
      navigate('/trips');
    },
    onError: (error) => {
      console.log(error);
    },
  });

  const changeDiaryDate = useMutation(
    ({ tripId, tripDate }) => modifyDate({ tripId, tripDate }),
    {
      onSuccess: () => {
        alert('변경되었습니다.');
        navigate('/trips');
      },
      onError: (error) => {
        console.log(error);
      },
    }
  );

  return (
    <>
      <div className="settingPage">
        <header className="settingHeader">
          <nav className="settingNav">
            <div className="nameArea">Triplet</div>
            <div>설정</div>
          </nav>
        </header>
        <main className="settingMain">
          <section className="innerMain">
            <form
              action="PATCH"
              onSubmit={handleChangeDate}
              className="modifyForm"
            >
              <section className="modifyTrip">
                <div>여행기간 수정</div>
                <p>여행 기간 변경을 할 수 있습니다.</p>
                <p>
                  여행 시작 전이라면 모두 변경 가능하지만, <br />
                  시작한 후라면 끝나는 날짜만 변경 가능합니다.
                </p>
              </section>
              <div className="modifyBtnArea">
                <section className="modifyDate">
                  <input
                    className="startDate"
                    type="date"
                    name="startDate"
                    value={tripDate.startDate}
                    onChange={handleChange}
                    required
                  />
                  <input
                    type="date"
                    name="endDate"
                    value={tripDate.endDate}
                    onChange={handleChange}
                    required
                  />
                </section>
                <button>수정하기</button>
              </div>
            </form>
            <hr className="hr" />
            <form action="DELETE" className="deleteForm" onClick={handleDelete}>
              <div>프로젝트 삭제</div>
              <button>삭제하기</button>
            </form>
          </section>
        </main>
        <aside>
          <figure className="logoArea">
            <img
              src="../../../assets/icons/shinhan-symbol.png"
              alt="logo"
              className="logo"
            />
            <figcaption>Triplet</figcaption>
          </figure>
          <img
            src="../../../assets/icons/sol.png"
            alt="sol캐릭터"
            className="solCharacter"
          />
        </aside>
      </div>
    </>
  );
}
