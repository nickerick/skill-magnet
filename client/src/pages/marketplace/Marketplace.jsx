import { useState } from 'react';
import { Button } from '@mui/material';
import CourseCard from '../../components/CourseCard';
import UserService from '../../services/UserService';

export default function Marketplace() {
  const [username, setUsername] = useState('');

  async function getUser() {
    var user = await UserService.getUser('bobert');
    console.log(user);
    setUsername(user.username);
  }

  return (
    <div>
      <p>This is the marketplace &#128526;</p>
      <CourseCard />
      <CourseCard />
      <CourseCard />
      <Button onClick={getUser}>Make API call</Button>
      <label>Fetched Username: {username}</label>
    </div>
  );
}
