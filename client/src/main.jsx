import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { ThemeProvider } from '@mui/material';

import Error from './pages/error/Error.jsx';
import Shell from './pages/shell/Shell.jsx';
import Home from './pages/home/Home.jsx';
import Marketplace from './pages/marketplace/Marketplace.jsx';
import theme from './theme';
import './main.css';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Shell />,
    errorElement: <Error />,
    children: [
      {
        path: 'home',
        element: <Home />,
      },
      {
        path: 'marketplace',
        element: <Marketplace />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>,
);
