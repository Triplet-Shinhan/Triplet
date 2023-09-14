import { createContext, useContext } from 'react';
import ExchangeApis from '../api/ExchangeApis';

export const ExchangeApiContext = createContext();

export function ExchangeApiProvider({ children }) {
  const exchange = new ExchangeApis();

  return (
    <ExchangeApiContext.Provider value={{ exchange }}>
      {children}
    </ExchangeApiContext.Provider>
  );
}

export function useExchangeApi() {
  return useContext(ExchangeApiContext);
}
