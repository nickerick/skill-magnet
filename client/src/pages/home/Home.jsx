import { useState } from 'react';

import { Box, Button } from '@mui/material';
import reactLogo from '/react.svg';
import viteLogo from '/vite.svg';

import './Home.css';

export default function Home() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div>
        <br />
        <br />
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <Button
          variant="contained"
          onClick={() => setCount(count => count + 1)}
        >
          I'm a MUI Button. Click me to update count.
        </Button>
        <Box border="2px solid" my={2} p={1} bgcolor="gray">
          I'm another MUI component demo. I hold text and show some inline
          styling tricks
        </Box>
        {count}
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}
