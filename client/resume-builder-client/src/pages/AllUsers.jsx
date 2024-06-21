import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Layout from "../components/Layout";

const USER_URL = "http://localhost:8080/api/user";

function AllUsers() {
    const [users, setUsers] = useState([]);
    const [success, setSuccess] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token,
            },
        };

        fetch(`${USER_URL}/all`, options)
            .then((res) => {
                if (res.status === 200) {
                    return res.json();
                } else if (res.status === 403) {
                    navigate("/");
                } else {
                    return Promise.reject(
                        `Unexpected status code ${res.status}`
                    );
                }
            })
            .then((data) => {
                console.log(data);
                setUsers(data);
            })
            .catch(() => {
                //localStorage.removeItem("jwtToken");
                //navigate("/");
            });
    }, []);

    const toggleUserDisable = (id) => {
        if (window.confirm(`Are you sure you want to disable user ${id}?`)) {
            const token = localStorage.getItem("jwtToken");
            const userToUpdate = users.filter((user) => user.userId === id)[0];
            userToUpdate.disabled = !userToUpdate.disabled;
            const options = {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + token,
                },
                body: JSON.stringify(userToUpdate),
            };
            fetch(`${USER_URL}/${id}`, options)
                .then((res) => {
                    if (res.status === 204) {
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
                        setSuccess(
                            `User ${id} ${
                                userToUpdate.disabled ? "disabled" : "enabled"
                            } successfully.`
                        );
                    } else {
                        setSuccess("");
                    }
                });
        }
    };

    return (
        <Layout className="w-max m-auto">
            <div className="flex flex-col gap-4 mt-10">
                <h2 className="text-center">All Users</h2>
                {success.length > 0 && (
                    <div className="bg-blue-100 p-2 rounded-md font-bold">
                        {success}
                    </div>
                )}
                <table className="w-max h-max all-users">
                    <thead>
                        <tr>
                            <th>User Id</th>
                            <th>Email</th>
                            <th>Username</th>
                            <th>Account Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.length > 0 &&
                            users.map((user) => {
                                return (
                                    <tr key={user.userId}>
                                        <td>{user.userId}</td>
                                        <td>{user.email}</td>
                                        <td>{user.username}</td>
                                        <td>
                                            <button
                                                className="border border-black p-1 rounded-lg hover:bg-slate-200"
                                                onClick={() =>
                                                    toggleUserDisable(
                                                        user.userId
                                                    )
                                                }
                                            >
                                                {user.disabled
                                                    ? "Disabled"
                                                    : "Enabled"}
                                            </button>
                                        </td>
                                    </tr>
                                );
                            })}
                    </tbody>
                </table>
            </div>
        </Layout>
    );
}

export default AllUsers;
