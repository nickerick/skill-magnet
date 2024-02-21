import { Link } from 'react-router-dom';
import { Button } from '@mui/material';
import './NavBar.css';
import search from '../../assets/search.svg'

export const NavBar = () => {
  return (
    <div className='navbar'>
      <Link to={`/`} className="title">SkillMagnet</Link>

      <div className='search-box'>
        <img src={search} className="search" alt="search icon" />
        <input type="text" placeholder='Explore courses...' />
      </div>

      <div className="menu-buttons">
        <Button variant="contained">
          <Link to={`marketplace`}> Course Marketplace </Link>
        </Button>
        <Button variant="contained">
          <Link to={`creatorstudio`}> Creator Studio </Link>
        </Button>
        <Button variant="contained">
          <Link to={`mycourses`}> My Courses </Link>
        </Button>
      </div>

      <div className="login-buttons">
        <Button variant="contained">
          <Link to={`login`}> Login </Link>
        </Button>
        <Button variant="contained">
          <Link to={`signup`}> Sign up </Link>
        </Button>
      </div>

    </div>

  );
};