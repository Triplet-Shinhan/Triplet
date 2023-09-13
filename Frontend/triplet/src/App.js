import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import Signup from './components/Opening/Signup/Signup';
import Login from './components/Opening/Login/Login';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { DiaryApiProvider } from './context/DiaryApiContext';
import { BankAccountProvider } from './context/BankAccountApiContext';
import { CookiesProvider } from 'react-cookie';
import DiaryInto from './components/Diary/DiaryInto/DiaryInto';
import DiarySetUp from './components/Diary/DiarySetUp/DiarySetUp';
import Exchange from './components/Exchange/Exchange';
import DiaryMain from './components/Diary/DiaryMain/DiaryMain';
import DiarySetting from './components/Diary/DiarySetting/DiarySetting';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      { index: true, element: <Login /> },
      { path: '/login', element: <Login /> },
      { path: '/signup', element: <Signup /> },
      { path: '/trips', element: <DiaryInto /> },
      { path: '/trips/setup', element: <DiarySetUp /> },
      { path: '/trips/:tripId/dailies', element: <DiaryMain /> },
      { path: '/exchange', element: <Exchange /> },
      { path: '/trips/:tripId/dailies/setting', element: <DiarySetting /> },
    ],
  },
]);

function App() {
  return (
    <CookiesProvider>
      <BankAccountProvider>
        <DiaryApiProvider>
          <QueryClientProvider client={queryClient}>
            <RouterProvider router={router} />
          </QueryClientProvider>
        </DiaryApiProvider>
      </BankAccountProvider>
    </CookiesProvider>
  );
}

export default App;
