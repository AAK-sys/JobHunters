# JobHunters

## Group #1

-   Tommy Liang (Developer 1)
-   Ahmed Abdel Kariem (Developer 2)

## HTD Capstone Project

### Problem Statement

-   Nowadays, submitting the same resume to multiple job postings isn't enough to land an interview.
-   We need to be able to cater our resume to what the job requests for (requirements, work experience, skills).
-   There is no easy way to change your resume so that it fits a company’s job post description.
-   To do so, we will need to change our resume directly and save it as another file for every job application (This is time and energy consuming).

### Technical Solution

-   Create an application that allows users to build a resume with information of their choice.
-   Users input all possible information that they think they will need to build a resume.
-   Build a resume with users selecting which information to use among their pool of inserted information.
-   Allow users to download that newly built resume as a pdf.

### Glossary (To be decided)

### High Level Requirement

-   User
    -   Can read, add, edit, and delete user information (userInfo, summary, education, experience, skills)
    -   Can create, download resume based on chosen resume template/supplied user information
-   Admin
    -   Can do what users do
    -   Can see all users of the application
    -   Can add, edit, and delete resume templates (Stretch Goal)
    -   Can disable a user access

### User Stories/Scenarios

-   User can view, add, edit, and delete their own Resume Information (both user and admin)

    -   User Info
        -   Full Name
        -   Email
        -   Phone Number
        -   Website
        -   Location
    -   Summary
        -   Brief Description
        -   Display Name
    -   Education
        -   University Name
        -   Degree
        -   Major
        -   GPA
        -   Start Date (must be in the past)
        -   End Date (must be after start date)
        -   Description
    -   Experience
        -   Company Name
        -   Role
        -   Display Name
        -   Start Date (must be in the past)
        -   End Date (must be after start date)
        -   Description
    -   Skills
        -   Skill Names

-   User can build resume after selecting Resume Information from their pool of information **(both user and admin)**
-   User can view, disable, add, and delete any user on the application **(only admins have this ability. Add and delete are stretch goals)**

  ### New Technologies
-    Tailwind CSS
-    Deployment (Stretch Goal)
