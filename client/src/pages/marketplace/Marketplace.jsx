import { useState } from 'react';
import { Button } from '@mui/material';
import CourseCard from '../../components/CourseCard';
import UserService from '../../services/UserService';
import './Marketplace.css'
import Header from '../../components/Header';
import VideoUploadExample from '../../components/example/VideoUploadExample';
import { Link } from 'react-router-dom';
import previous from '../../assets/left-arrow-icon.svg';
import next from '../../assets/right-arrow-icon.svg';


export default function Marketplace() {
  const [username, setUsername] = useState('');

  async function getUser() {
    var user = await UserService.getUser('bobert');
    console.log(user);
    setUsername(user.username);
  }

    const totalNumOfCourses = 9; //This needs to be changed once we know how many courses we have
    const [startIndex, setStartIndex] = useState(0);
    const numVisibleCards = 4; // Change this number to adjust the number of visible cards at a time

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
        <button className="prev-button" onClick={handlePrevClick} disabled={startIndex === 0}>
          <img src={previous} alt="Previous" />
        </button>
        <div className="courses-list">
          {Array.from({ length: numVisibleCards }, (_, i) => {
            const index = startIndex + i;
            return (
              <CourseCard
                key={index}
                courseTitle={`Course ${index + 1}`}
                imageUrl=""
                instructorName={`Instructor ${index + 1}`}
              />
            );
          })}
        </div>
        <button className="next-button" onClick={handleNextClick} disabled={startIndex === 9 - numVisibleCards}>
          <img src={next} alt="Next" />
        </button>
      </div>

      <Button onClick={getUser}>Make API call</Button>
      <label>Fetched Username: {username}</label>
    </div>
  );
}
