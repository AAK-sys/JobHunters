import React, { useState, useEffect } from 'react';
import {useNavigate} from 'react-router-dom';
import UserInfoForm from '../forms/UserInfoForm';
import DynamicForm from '../components/DynamicForm';
import ExperienceForm from '../forms/ExperienceForm';
import EducationForm from '../forms/EducationForm';
import Layout from '../components/Layout';
import { jwtDecode } from "jwt-decode";
import SummaryForm from '../forms/SummaryForm';

const AddInfo = () => {

  const [user, setUser] = useState({});
  const [userInfo, setUserInfo] = useState([]);
  const [summary, setSummary] = useState([]);
  const [education, setEducation] = useState([]);
  const [experience, setExperience] = useState([]);
  const [summaryInfo, setSummaryInfo] = useState({});
  const [experienceInfo, setExperienceInfo] = useState({});
  const [summaryOptions, setSummaryOptions] = useState([]);
  const [experienceOptions, setExperienceOptions] = useState([]);
  const [educationInfo, setEducationInfo] = useState({});
  const [educationOptions, setEducationOptions] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("jwtToken");
    if (!token) {
      navigate("/login");
      return;
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
          return Promise.reject(`Unexpected status code: ${res.status}`);
        }
      })
      .then((data) => {
        setUser(data);
        setSummary(data.summaries || []);
        setExperience(data.experiences || []);
        setEducation(data.education || []);
        setUserInfo(data.userInfo || defaultUserInfo)
      });
  }, [navigate]);

  useEffect(() => {

    const summaryMap = summary.reduce((acc, s) => {
      acc[s.summaryId] = s;
      return acc;
    }, {});

    const summaryOptionsArray = summary.reduce((acc, s) => {
      acc.push({ value: s.summaryId, label: s.displayName });
      return acc;
    }, []);
    

    const experienceMap = experience.reduce((acc, s) => {
      acc[s.experienceId] = s;
      return acc;
    }, {});
    

    const experienceOptionsArray = experience.reduce((acc, s) => {
      acc.push({ value: s.experienceId, label: s.displayName });
      return acc;
    }, []);

    const educationMap = education.reduce((acc, s) => {
      acc[s.educationId] = s;
      return acc;
    }, {});
    

    const educationOptionsArray = education.reduce((acc, s) => {
      acc.push({ value: s.educationId, label: s.universityName });
      return acc;
    }, []);

    const defaultExperience = {
      experienceId: 0,
      displayName: '',
      company: '',
      role: '',
      startDate: '',
      endDate: '',
      description: '',
      userId: user.userId
    };

    const defaultSummary = {
      summaryId: 0,
      displayName: '',
      description: '',
      userId: user.userId
    }

    const defaultEducation = {
      universityName: '',
      degree: '',
      major: '',
      gpa: '',
      startDate: '',
      endDate: '',
      description: '',
      userId: user.userId
    }
    
    educationMap[0] = defaultEducation;
    experienceMap[0] = defaultExperience;
    summaryMap[0] = defaultSummary;

    educationOptionsArray.push({ value: defaultEducation.educationId, label: 'Add new education' });
    experienceOptionsArray.push({ value: defaultExperience.experienceId, label: 'Add new experience' });
    summaryOptionsArray.push({value: defaultSummary.summaryId, label: 'Add new Summary'});


    setSummaryInfo(summaryMap);
    setSummaryOptions(summaryOptionsArray);
    setExperienceInfo(experienceMap);
    setExperienceOptions(experienceOptionsArray);
    setEducationInfo(educationMap);
    setEducationOptions(educationOptionsArray);

    console.log(user);

  }, [user]);

  const defaultUserInfo = {
    fullName: '',
    email: '',
    phone: '',
    website: '',
    location: '',
    userId: user.userId
  };

  const summaryForm = SummaryForm;
  const experienceForm = ExperienceForm;
  const educationForm = EducationForm;
  return (
    <Layout className="flex flex-col">
      <UserInfoForm formData={userInfo} setFormData={setUserInfo} />
      <DynamicForm
          title={"Summary"}
          information={summaryInfo}
          setInformation={setSummaryInfo}
          options={summaryOptions}
          setOptions={setSummaryOptions}
          FormComponent={summaryForm}
      />
      <DynamicForm
          title={"Experience"}
          information={experienceInfo}
          setInformation={setExperienceInfo}
          options={experienceOptions}
          setOptions={setExperienceOptions}
          FormComponent={experienceForm}
      />
            <DynamicForm
          title={"Education"}
          information={educationInfo}
          setInformation={setEducationInfo}
          options={educationOptions}
          setOptions={setEducationOptions}
          FormComponent={educationForm}
      />
    </Layout>
  );
};

export default AddInfo;