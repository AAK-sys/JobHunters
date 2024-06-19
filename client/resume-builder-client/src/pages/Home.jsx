import React, { useContext, useEffect } from "react";
import Layout from "../components/Layout";
import { UserContext } from "../context/UserContext";
import MyCarousel from "../components/MyCarousel";

function Home() {
    const { user, setUserContext } = useContext(UserContext);
    useEffect(() => {
        if (user) {
            const token = localStorage.getItem("jwtToken");
            setUserContext(token);
            console.log(user);
        }
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
