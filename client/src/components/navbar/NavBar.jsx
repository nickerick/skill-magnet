import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import {
  Button,
  Drawer,
  IconButton,
  List,
  ListItemButton,
  ListItemText,
  useMediaQuery,
  useTheme
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import './NavBar.css';
import search from '../../assets/search.svg'
import skillmagnetlogo from '../../assets/skillmagnet-logo.png'

export const NavBar = () => {

  const [openDrawer, setOpenDrawer] = useState(false);
  const theme = useTheme();
  console.log(theme);
  const isMatch = useMediaQuery(theme.breakpoints.down('xl'));
  console.log(isMatch);
  return (
    <div className='navbar'>

      <Link to={`/`}>
        <img src={skillmagnetlogo} className="skillmagnetlogo" alt="logo" /> {/* Add your logo image here */}
      </Link>

      <Link style={{color: 'white'}} to={`/`} className="title">SkillMagnet</Link>

      <div className='search-box'>
        <img src={search} className="search" alt="search icon" />
        <input type="text" placeholder='Explore courses...' />
      </div>

      <React.Fragment>
        {
          isMatch ? (
            <>
              <Drawer anchor={'right'} open={openDrawer} onClose={() => setOpenDrawer(false)}>
                <List>
                  <ListItemButton>
                      <ListItemText onClick={() => setOpenDrawer(false)}>
                        <Link style={{textDecoration: 'none', color: '#181d31'}} to={`marketplace`}> Course Marketplace </Link>
                      </ListItemText>
                  </ListItemButton>
                  <ListItemButton>
                    <ListItemText onClick={() => setOpenDrawer(false)}>
                      <Link style={{textDecoration: 'none', color: '#181d31'}} to={`creatorstudio`}> Creator Studio </Link>
                    </ListItemText>
                  </ListItemButton>
                  <ListItemButton>
                    <ListItemText onClick={() => setOpenDrawer(false)}>
                      <Link style={{textDecoration: 'none', color: '#181d31'}} to={`mycourses`}> My Courses </Link>
                    </ListItemText>
                  </ListItemButton>
                  <ListItemButton>
                    <ListItemText onClick={() => setOpenDrawer(false)}>
                      <Link style={{textDecoration: 'none', color: '#181d31'}} to={`loginsignup`}> Login/Sign Up</Link>
                    </ListItemText>
                  </ListItemButton>
                </List>
              </Drawer><IconButton sx={{color:'white',marginLeft: 'auto'}} onClick={() => setOpenDrawer(!openDrawer)}>
              <MenuIcon />
            </IconButton>
            </>
          ) : (
            <>
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
                  <Link to={`loginsignup`}> Login/Sign Up</Link>
                </Button>

              </div>
            </>
          )
        }
      </React.Fragment>
    </div>
  );
};