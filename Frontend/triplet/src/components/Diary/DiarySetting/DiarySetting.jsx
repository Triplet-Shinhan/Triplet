import React, { useState } from 'react';
import './DiarySetting.scss';

export default function DiarySetting() {
  const [tripDate, setTripDate] = useState({ startDate: '', endDate: '' });

  const handleSumbit = (e) => {
    e.preventDefault();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTripDate((date) => ({ ...date, [name]: value }));
  };
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
            <form action="PATCH" onSubmit={handleSumbit} className="modifyForm">
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
            <hr />
            <form action="DELETE" className="deleteForm">
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