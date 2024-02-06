import { Link } from 'react-router-dom';
import { Button } from '@mui/material';
import './NavBar.css';

export const NavBar = () => {
  return (
    <nav>
      <Link to={`home`} className="title">SkillMagnet</Link>

      <Button variant="outlined">
        <Link to={`marketplace`}> Course Marketplace </Link>
      </Button>

      <Button variant="outlined">
        <Link to={`creatorstudio`}> Creator Studio </Link>
      </Button>

      <Button variant="outlined">
        <Link to={`mycourses`}> My Courses </Link>
      </Button>


    </nav>
  );
};