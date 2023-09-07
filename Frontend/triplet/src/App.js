import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import Signup from './components/Opening/Signup/Signup';
import Login from './components/Opening/Login/Login';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import DiaryInto from './components/Diary/DiaryInto/DiaryInto';
import DiarySetUp from './components/Diary/DiarySetUp/DiarySetUp';
import { DiaryApiProvider } from './context/DiaryApiContext';

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      { index: true, element: <DiaryInto /> },
      { path: '/login', element: <Login /> },
      { path: '/signup', element: <Signup /> },
      { path: '/trips', element: <DiaryInto /> },
      { path: '/trips/setup', element: <DiarySetUp /> },
    ],
  },
]);

function App() {
  return (
    <DiaryApiProvider>
      <QueryClientProvider client={queryClient}>
        <RouterProvider router={router} />
      </QueryClientProvider>
    </DiaryApiProvider>
  );
}

export default App;
