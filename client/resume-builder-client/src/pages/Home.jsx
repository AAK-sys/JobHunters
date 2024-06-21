import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import Layout from "../components/Layout";
import MyCarousel from "../components/MyCarousel";

function Home() {
    const [user, setUser] = useState({});
    const navigate = useNavigate();
    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        if (!token) {
            navigate("/login");
        }
        const decodedData = jwtDecode(token);
        const options = {
            method: "GET",
            headers: {
                Authorization: "Bearer " + token,
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
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Layout>
            <div className="w-5/6 p-24 max-w-5xl mx-auto">
                <h2 className="text-center text-3xl mb-24">
                    Welcome back <span className="italic">{user.username}</span>
                    !
                </h2>
                <h3 className="text-center text-2xl mb-12">
                    Create Your Own Resume Now!
                </h3>
                {/* Carousel */}
                <div>
                    <h2 className="ml-10">
                        Choose a resume template to get started.
                    </h2>
                    <MyCarousel />
                </div>
            </div>
        </Layout>
    );
}

export default Home;
