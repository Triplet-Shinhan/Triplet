import React from 'react';
import './Loading.scss';

export default function Loading() {
  return (
    <div className="loading">
      <img src="../../../assets/icons/sol.png" alt="로고" />
      <div>Loading...</div>
    </div>
  );
}
