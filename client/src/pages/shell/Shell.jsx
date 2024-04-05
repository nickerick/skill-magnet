import { Outlet } from 'react-router';
import { NavBar } from '../../components/navbar/NavBar.jsx';
import './Shell.css';

export default function Shell() {
  return (
    <div id="app-shell">
      <div className="container">
        <NavBar />
        <div id="child-content">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
