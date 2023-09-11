import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import Signup from './components/Opening/Signup/Signup';
import Login from './components/Opening/Login/Login';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import DiaryInto from './components/Diary/DiaryInto/DiaryInto';
import DiarySetUp from './components/Diary/DiarySetUp/DiarySetUp';
import { DiaryApiProvider } from './context/DiaryApiContext';
import { BankAccountProvider } from './context/BankAccountApiContext';
import Exchange from './components/Exchange/Exchange';
import DiaryMain from './components/Diary/DiaryMain/DiaryMain';

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
      // { path: '/trips/:tripId/dailies', element: <Diary /> },
      { path: '/trips/dailies', element: <DiaryMain /> },
      { path: '/exchange', element: <Exchange /> },
    ],
  },
]);

function App() {
  return (
    <BankAccountProvider>
      <DiaryApiProvider>
        <QueryClientProvider client={queryClient}>
          <RouterProvider router={router} />
        </QueryClientProvider>
      </DiaryApiProvider>
    </BankAccountProvider>
  );
}

export default App;
