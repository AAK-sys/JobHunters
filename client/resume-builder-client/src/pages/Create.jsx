import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faPlus,
    faPenToSquare,
    faDownload,
} from "@fortawesome/free-solid-svg-icons";
import { jwtDecode } from "jwt-decode";
import { PDFDownloadLink, PDFViewer } from "@react-pdf/renderer";

import Layout from "../components/Layout";
import BuilderDropDown from "../components/BuilderDropDown";
import ResumeView from "../components/ResumeView";
import BuilderButton from "../components/BuilderAddButton";

const SKILL_URL = "http://localhost:8080/api/skill";

function Create() {
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

    const navigate = useNavigate();

    // fetch user info
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
                // populate user info into arrays
                setUserInfo(data.userInfo);
                setSummaries(data.summaries);
                setEducations(data.educations);
                setExperieneces(data.experiences);
            });
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
            <div className="w-full lg:ml-8 lg:mr-4 lg:my-0 mx-0 my-4 pb-16 md:pb-16 md:overflow-y-scroll">
                <h2 className="text-center text-2xl mt-8">
                    Select Info To Add To View Resume
                </h2>
                <div className="flex flex-col gap-8 mt-8">
                    {userInfo ? (
                        <div className="flex flex-col gap-4 text-center builder-info">
                            <h2>Your Profile</h2>
                            <div className="flex justify-between">
                                <h3>Full Name: {userInfo.fullName}</h3>
                                <h3>Email: {userInfo.email}</h3>
                            </div>
                            <div className="flex justify-between">
                                {userInfo.phone && (
                                    <h3>Phone: {userInfo.phone}</h3>
                                )}
                                {userInfo.website && (
                                    <h3>Website: {userInfo.website}</h3>
                                )}
                                {userInfo.location && (
                                    <h2>Location: {userInfo.location}</h2>
                                )}
                            </div>
                            <BuilderButton
                                className="profile-button"
                                icon={faPenToSquare}
                                text="Edit Profile"
                            />
                        </div>
                    ) : (
                        <div className="builder-dropdown">
                            <BuilderButton
                                className="profile-button"
                                icon={faPenToSquare}
                                text="Set Profile"
                            />
                        </div>
                    )}
                    {summaries && (
                        <div className="builder-dropdown">
                            <h2 className="mb-4">Select Summaries</h2>
                            <BuilderDropDown
                                objValue={selectSummaries}
                                handleChange={handleSummaryChange}
                                objArray={summaries}
                                label="displayName"
                                idString="summaryId"
                            />
                            <BuilderButton
                                className="summary-button"
                                icon={faPlus}
                                text="Add Summary"
                            />
                        </div>
                    )}
                    {educations && (
                        <div className="builder-dropdown">
                            <h2 className="mb-4">Select Educations</h2>
                            {educations && (
                                <BuilderDropDown
                                    objValue={selectEducations}
                                    handleChange={handleEducationChange}
                                    objArray={educations}
                                    label="universityName"
                                    idString="educationId"
                                />
                            )}
                            <BuilderButton
                                className="education-button"
                                icon={faPlus}
                                text="Add Education"
                            />
                        </div>
                    )}
                    {experiences && (
                        <div className="builder-dropdown">
                            <h2 className="mb-4">Select Experiences</h2>
                            {experiences && (
                                <BuilderDropDown
                                    objValue={selectExperiences}
                                    handleChange={handleExperienceChange}
                                    objArray={experiences}
                                    label="companyName"
                                    idString="experienceId"
                                />
                            )}
                            <BuilderButton
                                className="experience-button"
                                icon={faPlus}
                                text="Add Experience"
                            />
                        </div>
                    )}
                    {skills && (
                        <div className="builder-dropdown">
                            <h2 className="mb-4">Select Skills</h2>
                            <BuilderDropDown
                                objValue={selectSkills}
                                handleChange={handleSkillChange}
                                objArray={skills}
                                label="name"
                                idString="skillId"
                            />
                            <BuilderButton
                                className="skill-button"
                                icon={faPlus}
                                text="Add Skill"
                            />
                        </div>
                    )}
                </div>
            </div>
            <div className="w-full md:ml-4 md:mr-8 md:my-0 mx-0 my-4 md:overflow-y-scroll">
                <div className="flex flex-col gap-4 m-auto mt-4 lg:w-2/3 md:w-5/6 sm:w-5/6 border border-black p-1 rounded-md">
                    <PDFViewer height="900px" width="100%">
                        <ResumeView
                            userInfo={userInfo}
                            selectSummaries={selectSummaries}
                            selectEducations={selectEducations}
                            selectExperiences={selectExperiences}
                            selectSkills={selectSkills}
                        />
                    </PDFViewer>
                    <hr />
                    <PDFDownloadLink
                        document={
                            <ResumeView
                                userInfo={userInfo}
                                selectSummaries={selectSummaries}
                                selectEducations={selectEducations}
                                selectExperiences={selectExperiences}
                                selectSkills={selectSkills}
                            />
                        }
                        fileName="resume.pdf"
                    >
                        {({ blob, url, loading, error }) =>
                            loading ? (
                                <>"Loading document..."</>
                            ) : (
                                <div className="flex items-center border border-black rounded-lg p-2 w-max m-auto">
                                    <FontAwesomeIcon icon={faDownload} />
                                    <span className="ml-2">
                                        Download Resume as PDF
                                    </span>
                                </div>
                            )
                        }
                    </PDFDownloadLink>
                </div>
            </div>
        </Layout>
    );
}

export default Create;
