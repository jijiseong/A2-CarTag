import { createBrowserRouter } from 'react-router-dom';
import App from './App';
import { TrimPage } from './pages/TrimPage';
import { ModelPage } from './pages/ModelPage';
import { PATH } from './utils/url';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: PATH.trim,
        element: <TrimPage />,
      },
      {
        path: PATH.type,
        element: <ModelPage />,
      },
      {
        path: PATH.exterior,
        element: <div>outside 페이지</div>,
      },
      {
        path: PATH.interior,
        element: <div>inside 페이지</div>,
      },
      {
        path: PATH.option,
        element: <div>option 페이지</div>,
      },
      {
        path: PATH.result,
        element: <div>result 페이지</div>,
      },
    ],
  },
]);
