import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Layout from "../components/Layout";
import { UserContext } from "../context/UserContext";
import BuilderDropDown from "../components/BuilderDropDown";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import ResumeView from "../components/ResumeView";

const SKILL_URL = "http://localhost:8080/api/skill";

function Create() {
    const { user, setUserContext } = useContext(UserContext);
    const [userInfo, setUserInfo] = useState({});
    const [summaries, setSummaries] = useState([]);
    const [educations, setEducations] = useState([]);
    const [experiences, setExperieneces] = useState([]);
    const [skills, setSkills] = useState([]);

    // arrays
    const [selectSummaries, setSelectSummaries] = useState([]);
    const [selectEducations, setSelectEducations] = useState([]);
    const [selectExperiences, setSelectExperiences] = useState([]);
    const [selectSkills, setSelectSkills] = useState([]);

    // fetch user from context if there isn't one
    useEffect(() => {
        if (user) {
            const token = localStorage.getItem("jwtToken");
            setUserContext(token);

            // populate user info into arrays
            setUserInfo(user.userInfo);
            setSummaries(user.summaries);
            setEducations(user.educations);
            setExperieneces(user.experiences);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    // get all skills from db
    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "GET",
            headers: {
                Authorization: `Bearer ` + token,
            },
        };
        fetch(SKILL_URL, options)
            .then((res) => res.json())
            .then((data) => setSkills(data));
    }, []);

    const handleSummaryChange = (obj) => {
        setSelectSummaries(obj);
    };

    const handleEducationChange = (obj) => {
        setSelectEducations(obj);
    };

    const handleExperienceChange = (obj) => {
        setSelectExperiences(obj);
    };

    const handleSkillChange = (obj) => {
        setSelectSkills(obj);
    };

    return (
        <Layout className="flex flex-col md:flex-row md:justify-between">
            <div className="w-full md:ml-8 md:mr-2 md:my-0 mx-0 my-4 bg-blue-50">
                <h2>Select Info To Add</h2>
                <div className="flex flex-col gap-12">
                    <div className="border w-4/5">
                        <h2>Add User Info</h2>
                        {userInfo.email}
                    </div>
                    <div className="builder-dropdown">
                        <h2>Add Summaries</h2>
                        {summaries && (
                            <BuilderDropDown
                                objValue={selectSummaries}
                                handleChange={handleSummaryChange}
                                objArray={summaries}
                                label="displayName"
                                idString="summaryId"
                            />
                        )}
                        <Link
                            className="flex gap-2 items-center border border-black rounded-lg p-2 w-max float-right mt-2"
                            to="/user"
                        >
                            <FontAwesomeIcon icon={faPlus} />
                            <span>Add Summaries?</span>
                        </Link>
                    </div>
                    <div className="builder-dropdown">
                        <h2>Add Educations</h2>
                        {educations && (
                            <BuilderDropDown
                                objValue={selectEducations}
                                handleChange={handleEducationChange}
                                objArray={educations}
                                label="universityName"
                                idString="educationId"
                            />
                        )}
                    </div>
                    <div className="builder-dropdown">
                        <h2>Add Experiences</h2>
                        {experiences && (
                            <BuilderDropDown
                                objValue={selectExperiences}
                                handleChange={handleExperienceChange}
                                objArray={experiences}
                                label="companyName"
                                idString="experienceId"
                            />
                        )}
                    </div>
                    <div className="builder-dropdown">
                        <h2>Add Skills</h2>
                        <BuilderDropDown
                            objValue={selectSkills}
                            handleChange={handleSkillChange}
                            objArray={skills}
                            label="name"
                            idString="skillId"
                        />
                    </div>
                </div>
            </div>
            <div className="w-full md:ml-2 md:mr-8 md:my-0 mx-0 my-4 bg-blue-50">
                PDF RENDERER
                <ResumeView />
            </div>
        </Layout>
    );
}

export default Create;
