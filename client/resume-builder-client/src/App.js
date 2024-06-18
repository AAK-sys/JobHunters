import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import AddInfo from "./pages/AddInfo";
import AllUsers from "./pages/AllUsers";
import Home from "./pages/Home";
import Landing from "./pages/Landing";
import Login from "./components/Login";
import ResumeBuilder from "./pages/ResumeBuilder";
import SignUp from "./components/SignUp";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Landing />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<SignUp />} />
                <Route path="/home" element={<Home />} />
                <Route path="/users" element={<AllUsers />} />
                <Route path="resume-builder" element={<ResumeBuilder />} />
                <Route path="/user" element={<AddInfo />} />
            </Routes>
        </Router>
    );
}

export default App;
