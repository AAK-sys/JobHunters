import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Layout from "./Layout";

function SignUp() {
    const defaultSignUp = {
        email: "",
        userName: "",
        password: "",
        confirmPassword: "",
    };

    const navigate = useNavigate();
    const [user, setUser] = useState(defaultSignUp);

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
        console.log("user signs up and get sent to login");
        alert("you are signed up");
        navigate("/");
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
                            required
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="userName">Username</label>
                        <input
                            type="text"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="userName"
                            value={user.userName}
                            onChange={handelChange}
                            required
                        />
                    </fieldset>

                    <fieldset className="mb-4 flex flex-col">
                        <label htmlFor="password"> Password </label>
                        <input
                            type="password"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="password"
                            value={user.password}
                            onChange={handelChange}
                            required
                        />
                    </fieldset>

                    <fieldset className="mb-2 flex flex-col">
                        <label htmlFor="confirmPassword">
                            Confirm Password
                        </label>
                        <input
                            type="password"
                            className="border rounded-lg px-2 py-1 shadow-md"
                            id="confirmPassword"
                            value={user.confirmPassword}
                            onChange={handelChange}
                            required
                        />
                    </fieldset>

                    <button
                        className="my-4 border-2 rounded-lg p-1 w-full"
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
