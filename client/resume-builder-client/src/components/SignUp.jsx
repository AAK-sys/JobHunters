import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Layout from "./Layout";

const AUTH_URL = "http://localhost:8080/api/auth";

function SignUp() {
    const defaultSignUp = {
        email: "",
        username: "",
        password: "",
        confirmPassword: "",
    };

    const navigate = useNavigate();
    const [user, setUser] = useState(defaultSignUp);
    const [errors, setErrors] = useState([]);
    const [success, setSuccess] = useState("");

    const handelChange = (event) => {
        const fieldName = event.target.id;
        const fieldValue = event.target.value;

        setUser(() => ({
            ...user,
            [fieldName]: fieldValue,
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (user.password !== user.confirmPassword) {
            setErrors(["Password and confirm password do not match."]);
            return;
        }
        const userObj = {
            userId: 0,
            username: user.username,
            password: user.password,
            email: user.email,
        };
        const options = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userObj),
        };
        fetch(`${AUTH_URL}/signup`, options)
            .then((res) => {
                if (res.status === 201) {
                    return null;
                } else if (res.status === 400) {
                    return res.json();
                } else {
                    return Promise.reject(
                        `Unexpected status code: ${res.status}`
                    );
                }
            })
            .then((data) => {
                if (!data) {
                    setErrors([]);
                    setSuccess("Your account has been created.");
                    setTimeout(() => {
                        navigate("/login");
                    }, 3000);
                } else {
                    setErrors(data);
                }
            });
    };

    return (
        <Layout className="bg-slate-100">
            <form
                onSubmit={handleSubmit}
                className="shadow-lg rounded-xl w-11/12 max-w-md m-auto p-8 text-lg bg-white"
            >
                <legend className="text-2xl text-center">Sign Up</legend>
                <div>
                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="email">Email</label>
                        <input
                            type="text"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="email"
                            value={user.email}
                            onChange={handelChange}
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="username"
                            value={user.username}
                            onChange={handelChange}
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="password"> Password </label>
                        <input
                            type="password"
                            autoComplete="on"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="password"
                            value={user.password}
                            onChange={handelChange}
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="confirmPassword">
                            Confirm Password
                        </label>
                        <input
                            type="password"
                            autoComplete="off"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="confirmPassword"
                            value={user.confirmPassword}
                            onChange={handelChange}
                        />
                    </fieldset>
                    {success && (
                        <div className="mb-4 bg-blue-200 p-4 rounded-lg">
                            <p className="font-bold w-max">{success}</p>
                            <p className="font-bold w-max">
                                Redirecting to login...
                            </p>
                        </div>
                    )}
                    {errors.length > 0 && (
                        <div className="mb-4 bg-red-200 p-4 rounded-lg">
                            <p className="font-bold border-b-2 border-gray-400 w-max">
                                The Following Errors Were Found
                            </p>
                            <ul>
                                {errors.length > 0 &&
                                    errors.map((error) => (
                                        <li
                                            key={error}
                                            className="list-disc ml-4 mt-2"
                                        >
                                            {error}
                                        </li>
                                    ))}
                            </ul>
                        </div>
                    )}

                    <button
                        className="mb-4 border-2 rounded-lg p-1 w-full sign-up-button"
                        type="submit"
                    >
                        Sign up!
                    </button>
                    <div className="flex justify-between">
                        <p>Already have an account?</p>
                        <Link className="text-blue-700" to="/login">
                            Sign In
                        </Link>
                    </div>
                </div>
            </form>
        </Layout>
    );
}

export default SignUp;
