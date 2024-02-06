import { Outlet } from 'react-router';
import { NavBar } from '../../components/navbar/NavBar.jsx';


export default function Shell() {
  return (
    <div id="app-shell">
      <div id="navbar">
        <NavBar />
      </div>


      <div id="child-content">
        <Outlet />
      </div>
    </div>
  );
}
