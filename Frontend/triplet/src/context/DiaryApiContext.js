import { createContext, useContext } from 'react';
import DiaryApis from '../api/DiaryApis';

export const DiaryApiContext = createContext();

export function DiaryApiProvider({ children }) {
  const diary = new DiaryApis();

  return (
    <DiaryApiContext.Provider value={{ diary }}>
      {children}
    </DiaryApiContext.Provider>
  );
}

export function useDiaryApi() {
  return useContext(DiaryApiContext);
}
