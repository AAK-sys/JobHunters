import React from "react";
import { Link } from "react-router-dom";
import Layout from "../components/Layout";

function Landing() {
    return (
        <Layout>
            <div className="text-center w-4/5 m-auto">
                <div className="mb-12">
                    <h2 className="mb-8 text-3xl">A New Way To Create</h2>
                    <p className="text-lg">
                        Resume Builder is a platform to help you cater your
                        resume to companies as you seem fit.
                    </p>
                </div>
                <Link
                    className="border rounded-full py-2 px-4 bg-blue-400 text-lg"
                    to="/signup"
                >
                    Create Account
                </Link>
            </div>
        </Layout>
    );
}

export default Landing;
