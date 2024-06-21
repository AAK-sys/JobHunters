import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Layout from "./Layout";

const AUTH_URL = "http://localhost:8080/api/user";

const DEFAULT_CREDENTIALS = {
    username: "",
    password: "",
};

function Login() {
    const [credentials, setCredentials] = useState(DEFAULT_CREDENTIALS);
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleChange = (event) => {
        const fieldName = event.target.id;
        const fieldValue = event.target.value;

        setCredentials(() => ({
            ...credentials,
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
            body: JSON.stringify(credentials),
        };
        fetch(`${AUTH_URL}/authenticate`, options)
            .then((res) => {
                if (res.status === 200) {
                    return res.json();
                } else if (res.status === 403) {
                    return null;
                } else {
                    return Promise.reject(
                        `Unexpected status code: ${res.status}`
                    );
                }
            })
            .then((data) => {
                if (!data) {
                    setError("The credentials were invalid.");
                } else {
                    localStorage.setItem("jwtToken", data.jwt_token);
                    navigate("/home");
                }
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
                            value={credentials.username}
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
                            value={credentials.password}
                            onChange={handleChange}
                        />
                    </fieldset>
                    {error && (
                        <div className="mb-4 bg-red-200 p-4 rounded-lg">
                            <p className="font-bold border-b-2 border-gray-400 w-max">
                                The Following Errors Were Found
                            </p>

                            <li key={error} className="list-disc ml-4 mt-2">
                                {error}
                            </li>
                        </div>
                    )}
                    <button
                        className="mb-4 border-2 rounded-lg p-1 w-full sign-up-button"
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
