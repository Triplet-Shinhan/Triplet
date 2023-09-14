import React from 'react';
import './DiaryDetail.scss';

export default function DiaryDetail() {
  return (
    <div className="diaryDetailPage">
      <header className="detailHeader">
        <nav className="detailName">Triplet</nav>
      </header>
      <main className="detailMain">
        <section className="expendSec">
          <section className="loginSec"></section>
          <section className="viewSec"></section>
          <section className="eachSec"></section>
        </section>
        <section className="imgSec">
          <img className="imgSrc" src="../../../assets/sample.JPG" />
        </section>
      </main>
    </div>
  );
}
