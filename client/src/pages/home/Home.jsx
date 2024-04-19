import { useState } from 'react';
import './Home.css';
import cover from '../../assets/site_background.jpg';
import enroll_courses_icon from '../../assets/enroll-courses-icon.png';
import notepad_icon from '../../assets/notepad-icon.png';
import take_quizzes_icon from '../../assets/take-quizzes-icon.png';

export default function Home() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div className="homepage-photo">
        <img src={cover} style={{width: '100vw'}}/>
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

      <div className="feature-section">
        <div className="feature-container">
          <div className="feature">
            <img className="feature-icon"
                 src={enroll_courses_icon}
                 alt="Enroll in Courses Icon"
                 height={40}/>
            <h3 className="feature-title">Discover and Enroll in Courses</h3>
            <p className="feature-description">Explore our selection of courses and topics. Users can easily browse through
              our course marketplace and enroll in courses that align with their interests and learning objectives. With just a few
              clicks, embark on your educational journey and start learning at your own pace.</p>
          </div>
          <div className="feature">
            <img className="feature-icon"
                 src={notepad_icon}
                 alt="Take Notes Icon" />
            <h3 className="feature-title">Take Notes</h3>
            <p className="feature-description">Utilize our integrated notepad feature to jot down important insights, key takeaways,
              and personal reflections as you progress through each course. Keep your study materials organized and easily accessible,
              ensuring that you can review and reinforce your learning whenever necessary.</p>
          </div>
          <div className="feature">
            <img className="feature-icon"
                 src={take_quizzes_icon}
                 alt="Assess Your Knowledge Icon" />
            <h3 className="feature-title">Assess Your Knowledge</h3>
            <p className="feature-description"> Challenge yourself and reinforce your learning with our dynamic quizzes tailored to
              each course. Identify areas for improvement and solidify your understanding, whether you're preparing for exams or
              simply reinforcing your knowledge.</p>
          </div>
        </div>
      </div>

    </>
  );
}
