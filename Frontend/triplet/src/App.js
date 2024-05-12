import { Routes, Route } from 'react-router-dom';
import './App.css';
import Signup from './components/Opening/Signup/Signup';
import Login from './components/Opening/Login/Login';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { DiaryApiProvider } from './context/DiaryApiContext';
import { BankAccountProvider } from './context/BankAccountApiContext';
import { CookiesProvider } from 'react-cookie';
import { ExchangeApiProvider } from './context/ExchangeApiContext';
import DiaryInto from './components/Diary/DiaryInto/DiaryInto';
import DiarySetUp from './components/Diary/DiarySetUp/DiarySetUp';
import Exchange from './components/Exchange/Exchange';
import DiaryMain from './components/Diary/DiaryMain/DiaryMain';
import DiarySetting from './components/Diary/DiarySetting/DiarySetting';
import DiaryDetail from './components/Diary/DiaryDetail/DiaryDetail';

const queryClient = new QueryClient();

function App() {
  const isLoggedIn = sessionStorage.getItem('isLoggedIn');

  return (
    <CookiesProvider>
      <DiaryApiProvider>
        <BankAccountProvider>
          <ExchangeApiProvider>
            <QueryClientProvider client={queryClient}>
              <Routes>
                <Route path="/" element={isLoggedIn ? <DiaryInto /> : <Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/trips" element={<DiaryInto />} />
                <Route path="/trips/setup" element={<DiarySetUp />} />
                <Route path="/trips/:tripId/dailies" element={<DiaryMain />} />
                <Route path="/exchange" element={<Exchange />} />
                <Route path="/trips/:tripId/dailies/:dailyId" element={<DiaryDetail />} />
                <Route path="/trips/:tripId/dailies/setting" element={<DiarySetting />} />
              </Routes>
              <ReactQueryDevtools initialIsOpen={true} />
            </QueryClientProvider>
          </ExchangeApiProvider>
        </BankAccountProvider>
      </DiaryApiProvider>
    </CookiesProvider>
  );
}

export default App;
