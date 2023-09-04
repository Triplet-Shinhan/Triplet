import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import Signup from './components/Opening/Signup/Signup';
import Login from './components/Opening/Login/Login';

const router = createBrowserRouter([
  {
    path: '/',
    children: [
      { index: true, element: <Login /> },
      { path: '/login', element: <Login /> },
      { path: '/signup', element: <Signup /> },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
