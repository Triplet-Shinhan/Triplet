import React from 'react';
import './Loading.scss';

export default function Loading() {
  return (
    <div className="loading">
      <img src="/assets/icons/sol.webp" alt="로고" />
      <div>Loading...</div>
    </div>
  );
}
