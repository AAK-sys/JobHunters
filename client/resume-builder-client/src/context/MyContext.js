import React, { createContext, useState } from 'react';

const MyContext = createContext();

const UserProvider = ({ children }) => {

    //User context

    const [user, setUser] = useState();
    

    return (
        <MyContext.Provider value={{ 
            user,
            setUser
        }}>
        {children}
        </MyContext.Provider>
    );
}

export { MyContext, UserProvider };