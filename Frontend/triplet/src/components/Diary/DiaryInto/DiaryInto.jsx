import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDiaryApi } from '../../../context/DiaryApiContext';
import { useQuery } from '@tanstack/react-query';
import DiaryPreview from '../DiaryPreview/DiaryPreview';

export default function DiarySettings() {
  // 로그인 사용자 세션에서 가져오기 => 안녕하세요 뒤에 뿌리기
  // 프로젝트 있는지 쿼리로 확인하고 없으면 현재 생성된 프로젝트가 없습니다. 새 프로젝트를 생성해주세요.
  // 있으면 리스트 불러오기
  const navigate = useNavigate();
  // 다이어리 API 사용하겠다는 의미
  const { diaryFunc } = useDiaryApi();

  // 백에서 diary api로 data 가져옴
  const {
    isLoading,
    error,
    data: tripList,
  } = useQuery(
    ['diary'],
    () => {
      return diaryFunc.viewProject();
    },
    { staleTime: 1000 * 6 * 1 }
  );

  return (
    <>
      <h1>안녕하세요.</h1>
      <h2>신한 해커톤님</h2>
      <ul>
        <li>
          프로젝트 영역
          {/* tripList 형식 오는거 확인해볼 것 undefined인지 아닌지 */}
          {tripList === undefined ? (
            <span>
              현재 생성된 프로젝트가 없습니다. 새 프로젝트를 생성해주세요.
            </span>
          ) : (
            tripList.map((diary) => <DiaryPreview diaryInfo={diary} />)
          )}
        </li>
        <li>
          <button>환전신청하러가기</button>
        </li>
        <li>
          <button onClick={() => navigate('/trips/setup')}>
            새 프로젝트 생성하기
          </button>
        </li>
      </ul>
    </>
  );
}
