import { Link } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function NavBar() {
    const [isAdmin, setIsAdmin] = useState(false);
    const [isUser, setIsUser] = useState(false);
    const token = localStorage.getItem("jwtToken");
    const navigate = useNavigate();

    useEffect(() => {
        if (token !== null) {
            const decodedToken = jwtDecode(token);
            setIsAdmin(decodedToken.authorities.includes("ADMIN"));
            setIsUser(decodedToken.authorities.includes("USER"));
        }
    }, [token]);

    const signOut = () => {
        localStorage.removeItem("jwtToken");
        navigate("/");
    };

    return (
        <nav className="bg-blue-200">
            <div className="flex justify-between items-center mx-8 h-14 text-xl">
                <h1 className="text-2xl">
                    <Link to={token ? "/home" : "/"}>Resume Builder</Link>
                </h1>
                {token === null && <Link to={"/login"}>Login</Link>}
                {isAdmin && (
                    // Navigation for admin
                    <div className="w-max">
                        <Link className="mx-4" to={"/users"}>
                            View All Users
                        </Link>
                        <Link className="mx-4" to={"/create"}>
                            Create
                        </Link>
                        <Link className="mx-4" to={"/user"}>
                            Add Info
                        </Link>
                        <button
                            className="ml-4 text-xl"
                            onClick={() => signOut()}
                        >
                            Sign Out
                        </button>
                    </div>
                )}
                {!isAdmin && isUser && (
                    // Navigation for users
                    <div className="w-max">
                        <Link className="mx-4" to={"/create"}>
                            Create
                        </Link>
                        <Link className="mx-4" to={"/user"}>
                            Add Info
                        </Link>
                        <button
                            className="ml-4 text-xl"
                            onClick={() => signOut()}
                        >
                            Sign Out
                        </button>
                    </div>
                )}
            </div>
        </nav>
    );
}

export default NavBar;
