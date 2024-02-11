import { useState } from 'react';
import { Button } from '@mui/material';
import CourseCard from '../../components/CourseCard';
import UserService from '../../services/UserService';
import './Marketplace.css'

export default function Marketplace() {
  const [username, setUsername] = useState('');

  async function getUser() {
    var user = await UserService.getUser('bobert');
    console.log(user);
    setUsername(user.username);
  }

  return (
    <div>
    <div className="courses-list-container">
      <div className="courses-list">
        <CourseCard courseTitle="Course 1" imageUrl="" instructorName="Instructor 1"  />
        <CourseCard courseTitle="Course 2" imageUrl="" instructorName="Instructor 2"  />
        <CourseCard courseTitle="Course 3" imageUrl="" instructorName="Instructor 3" />
        <CourseCard courseTitle="Course 4" imageUrl="" instructorName="Instructor 4"  />
        <CourseCard courseTitle="Course 5" imageUrl="" instructorName="Instructor 5"  />
        <CourseCard courseTitle="Course 6" imageUrl="" instructorName="Instructor 6" />
        <CourseCard courseTitle="Course 7" imageUrl="" instructorName="Instructor 7"  />
        <CourseCard courseTitle="Course 8" imageUrl="" instructorName="Instructor 8"  />
        <CourseCard courseTitle="Course 9" imageUrl="" instructorName="Instructor 9" />
      </div>
    </div>
      <Button onClick={getUser}>Make API call</Button>
      <label>Fetched Username: {username}</label>
    </div>
  );
}
