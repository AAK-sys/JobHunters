import React, { createContext, useState } from "react";
import { jwtDecode } from "jwt-decode";

const UserContext = createContext({});

const UserProvider = ({ children }) => {
    const [user, setUser] = useState({});

    const setUserContext = (tokenData) => {
        const decodedData = jwtDecode(tokenData);
        const options = {
            method: "GET",
            headers: {
                Authorization: "Bearer " + tokenData,
            },
        };
        fetch(`http://localhost:8080/api/user?name=${decodedData.sub}`, options)
            .then((res) => {
                if (res.status === 200) {
                    return res.json();
                } else {
                    return Promise.reject(
                        `Unexpected status code: ${res.status}`
                    );
                }
            })
            .then((data) => {
                setUser(data);
            });
    };

    return (
        <UserContext.Provider value={{ user, setUserContext }}>
            {children}
        </UserContext.Provider>
    );
};

export { UserContext, UserProvider };
