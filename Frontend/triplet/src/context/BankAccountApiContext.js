import { createContext, useContext } from 'react';
import BankAccount from '../api/BankAccount';

export const BankAccountApiContext = createContext();

export function BankAccountProvider({ children }) {
  const bank = new BankAccount();

  return (
    <BankAccountApiContext.Provider value={{ bank }}>
      {children}
    </BankAccountApiContext.Provider>
  );
}

export function useBankApi() {
  return useContext(BankAccountApiContext);
}
