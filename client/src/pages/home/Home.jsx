import { useState } from 'react';

import { Box, Button } from '@mui/material';
import reactLogo from '/react.svg';
import viteLogo from '/vite.svg';

import './Home.css';
import cover from '../../assets/site_background.jpg';

export default function Home() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="homepage-photo">
        <img src={cover} />
        <div className="text-card">
          <h1>One stop shop for your learning needs</h1>
          <h2>Educational content and study tools in one place</h2>
        </div>
      </div>

      <div className="about-us">
        <h1>What makes SkillMagnet unique</h1>
        <h3>Our goal is to provide a centralized platform for students to access quality educational resources and
          user-driven study tools.
          <br /><br />
          Our platform will make learning more efficient and effective by organizing content into lessons, tracking user
          progress, and offering study tools like flashcards and quizzes.
          <br /><br />
          We aim to make learning more efficient and effective by offering a well-structured and interactive educational
          environment.
          <br /><br />
        </h3>
      </div>

      <div className="quotes">

      </div>

    </>
  );
}
