import { createBrowserRouter } from 'react-router-dom';
import App from './App';
import { TrimPage } from './pages/TrimPage';
import { ModelTypePage } from './pages/ModelTypePage';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: 'trim',
        element: <TrimPage />,
      },
      {
        path: 'type',
        element: <ModelTypePage />,
      },
      {
        path: 'outside',
        element: <div>outside 페이지</div>,
      },
      {
        path: 'inside',
        element: <div>inside 페이지</div>,
      },
      {
        path: 'option',
        element: <div>option 페이지</div>,
      },
      {
        path: 'result',
        element: <div>result 페이지</div>,
      },
    ],
  },
]);
