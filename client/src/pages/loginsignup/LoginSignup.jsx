import './LoginSignup.css';
import React, { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import user_icon from '../../assets/person.png';
import email_icon from '../../assets/email.png';
import password_icon from '../../assets/password.png';
import { Button } from '@mui/material';

const LoginSignup = () => {
  const [action, setAction] = useState("Sign Up");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const allFieldsFilled = (action === "Sign Up" && username && email && password) ||
    (action === "Login" && username && password);

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = () => {
    //testing line
    window.alert("form submitted. username is " + username + " and password is " + password);
  };

  return (
    <div className='loginsignup'>
      <ToastContainer></ToastContainer>
      <div className='loginsignup-header'>
        <div className='signup-text'>{action}</div>
        <div className="underline"></div>
      </div>

      <div className="inputs">
        <div className="input">
          <img src={user_icon} alt="" />
          <input type="text" placeholder="Username" value={username} onChange={handleUsernameChange} />
        </div>

        {action === 'Login' ? null : (
          <div className="input">
            <img src={email_icon} alt="" />
            <input type="email" placeholder="Email" value={email} onChange={handleEmailChange} />
          </div>
        )}

        <div className="input">
          <img src={password_icon} alt="" />
          <input type="password" placeholder="Password" value={password} onChange={handlePasswordChange} />
        </div>
      </div>

      {allFieldsFilled ? (
        <div className="submit-container">
          <Button
            variant="contained"
            size="large"
            onClick={handleSubmit}>Submit</Button>
        </div>
      ) : (
        <div className="submit-container">
          <Button
            variant="contained"
            size="large"
            onClick={() => { setAction("Sign Up") }}
            className="submit"
            style={{ backgroundColor: action === "Sign Up" ? "#181d31" : "#7f7e7e" }}>Sign Up</Button>
          <Button
            variant="contained"
            size="large"
            onClick={() => { setAction("Login") }}
            className="submit"
            style={{ backgroundColor: action === "Login" ? "#181d31" : "#7f7e7e" }}>Login</Button>
        </div>
      )}
    </div>
  );
};

export default LoginSignup;
