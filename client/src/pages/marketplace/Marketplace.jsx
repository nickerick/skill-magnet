import { useEffect, useState } from 'react';
import { Button } from '@mui/material';
import CourseCard from '../../components/CourseCard';
import './Marketplace.css';
import Header from '../../components/Header';
import { Link } from 'react-router-dom';
import previous from '../../assets/left-arrow-icon.svg';
import next from '../../assets/right-arrow-icon.svg';
import CourseService from '../../services/CourseService';

export default function Marketplace() {
  const [courses, setCourses] = useState([]);
  const [startIndex, setStartIndex] = useState(0);

  const totalNumOfCourses = courses.length;
  const numVisibleCards = 4; // Change this number to adjust the number of visible cards at a time

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const coursesData = await CourseService.getAllCourses();
        console.log(coursesData);
        setCourses(coursesData);
      } catch (error) {
        alert('ERROR: Failed to fetch courses');
      }
    };

    fetchCourses();
  }, []);

  const handleNextClick = () => {
    if (startIndex < totalNumOfCourses - numVisibleCards) {
      setStartIndex(startIndex + 1);
    }
  };

  const handlePrevClick = () => {
    if (startIndex > 0) {
      setStartIndex(startIndex - 1);
    }
  };

  return (
    <div>
      <div className="header-and-filters-container">
        <Header headerName="Course Marketplace" />
        <div className="filter-options">
          <Button variant="contained">
            <Link to={``}>All Courses</Link>
          </Button>
          <Button variant="contained">
            <Link to={``}>Topics</Link>
          </Button>
          <Button variant="contained">
            <Link to={``}>Cart</Link>
          </Button>
        </div>
      </div>
      <div className="courses-list-container">
        <button
          className="prev-button"
          onClick={handlePrevClick}
          disabled={startIndex === 0}
        >
          <img src={previous} alt="Previous" />
        </button>
        <div className="courses-list">
          {courses.slice(startIndex, startIndex + numVisibleCards).map(c => (
            <CourseCard
              key={c.id}
              courseId={c.id}
              courseTitle={c.title}
              imageUrl=""
              description={c.description}
            />
          ))}
        </div>
        <button
          className="next-button"
          onClick={handleNextClick}
          disabled={startIndex === totalNumOfCourses - numVisibleCards}
        >
          <img src={next} alt="Next" />
        </button>
      </div>
    </div>
  );
}
