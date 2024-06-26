import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { ThemeProvider } from '@mui/material';

import Error from './pages/error/Error.jsx';
import Shell from './pages/shell/Shell.jsx';
import Home from './pages/home/Home.jsx';
import Marketplace from './pages/marketplace/Marketplace.jsx';
import Example from './pages/example/Example.jsx';
import theme from './theme';
import './main.css';
import CreatorStudio from './pages/creatorstudio/CreatorStudio.jsx';
import MyCourses from './pages/mycourses/MyCourses.jsx';
import CourseViewer from './pages/courseviewer/CourseViewer.jsx';
import LoginSignup from './pages/loginsignup/LoginSignup.jsx'
import CreateNewCourse from './pages/createnewcourse/CreateNewCourse.jsx'; 
import EditExistingCourse from './pages/editexistingcourse/EditExistingCourse.jsx';
import Quiz from './pages/quiz/Quiz.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Shell />,
    errorElement: <Error />,
    children: [
      {
        path: '/',
        element: <Home />,
      },
      {
        path: 'marketplace',
        element: <Marketplace />,
      },
      // Hide path from public
      // {
      //   path: 'example',
      //   element: <Example />,
      // },
      {
        path: 'creatorstudio',
        element: <CreatorStudio />,
      },
      {
        path: 'mycourses',
        element: <MyCourses />,
      },
      {
        path: 'courseviewer/:courseId',
        element: <CourseViewer />,
      },
      {
        path: 'loginsignup',
        element: <LoginSignup />,
      },
      {
        path: 'createnewcourse', 
        element: <CreateNewCourse />,
      }, 
      {
        path: 'editexistingcourse',
        element: <EditExistingCourse />, 
      },
      {
        path: 'quiz/:quizId',
        element: <Quiz />,
      }
    ]
  }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>,
);
