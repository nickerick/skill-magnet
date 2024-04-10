import React, { createContext, useContext, useState } from 'react';

const UserContext = createContext();

export const useUser = () => useContext(UserContext);


export const UserProvider = ({ children }) => {
  const [userId, setUserId] = useState(null);


  const login = (id) => {
    setUserId(id);
  };

  const logout = () => {
    setUserId(null);
  };

  const value = {
    userId,
    login,
    logout
  };

  return (
    <UserContext.Provider value={value}>
      {children}
    </UserContext.Provider>
  );
};
