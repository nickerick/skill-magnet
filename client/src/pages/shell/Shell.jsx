import { Outlet } from 'react-router';
import { Link } from 'react-router-dom';
import { Button } from '@mui/material';

export default function Shell() {
  return (
    <div id="app-shell">
      <div id="navbar">
        SUPER COOL NAVBAR
        <br />
        <Button variant="outlined">
          <Link to={`home`}> Home </Link>
        </Button>
        <Button variant="outlined">
          <Link to={`marketplace`}> Marketplace </Link>
        </Button>
      </div>
      <div id="child-content">
        <Outlet />
      </div>
    </div>
  );
}
