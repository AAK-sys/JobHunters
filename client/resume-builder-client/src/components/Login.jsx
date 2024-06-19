import { useContext } from 'react';
import { MyContext } from '../context/MyContext';
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../api/auth";
import Layout from "./Layout";


const URL = "http://localhost:8080/api/auth";

const defaultLogin = {
    username: "",
    password: "",
};

function Login() {

    const { user, setUser} = useContext(MyContext);
    setUser(defaultLogin);

    const [errors, setErrors] = useState([]);
    const navigate = useNavigate();

    const handleChange = (event) => {
        const fieldName = event.target.id;
        const fieldValue = event.target.value;

        setUser(() => ({
            ...user,
            [fieldName]: fieldValue,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        };
        fetch(`${URL}/login`, options)
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
                if (!data.jwt_token) {
                    setErrors(data);
                }
                localStorage.setItem("jwtToken", data.jwt_token);
                navigate("/home");
            });
    };

    return (
        <Layout className="bg-slate-100">
            <form
                onSubmit={handleSubmit}
                className="shadow-lg rounded-xl w-11/12 max-w-md m-auto p-8 text-lg bg-white"
            >
                <legend className="text-2xl text-center">Login</legend>
                <div>
                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="username"
                            value={user.username}
                            onChange={handleChange}
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="password"> Password</label>
                        <input
                            type="password"
                            autoComplete="on"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="password"
                            value={user.password}
                            onChange={handleChange}
                        />
                    </fieldset>

                    <button
                        className="my-4 border-2 rounded-lg p-1 w-full"
                        type="submit"
                    >
                        Sign In
                    </button>
                    <div className="flex justify-between">
                        <p>Don't have an account?</p>
                        <Link className="text-blue-700" to="/signup">
                            Sign up now!
                        </Link>
                    </div>
                </div>
            </form>
        </Layout>
    );
}

export default Login;
