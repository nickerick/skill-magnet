import React, { useState } from "react";
import Header from "../../components/Header";
import BasicCourseInformation from "../../components/courseinformation/BasicCourseInformation";
import BasicLessonInformation from "../../components/courseinformation/BasicLessonInformation";
import plusIcon from "../../assets/math-plus-icon.svg";
import "./CreateNewCourse.css";

export default function CreateNewCourse() {
    const [lessons, setLessons] = useState([<BasicLessonInformation key={0} />]);

    const addLesson = () => {
        setLessons([...lessons, <BasicLessonInformation key={lessons.length} />]);
    };

    return (
        <div>
            <div className="create-course">
                <div className="page-title">
                    <Header headerName="Course Information" />
                </div>
                <BasicCourseInformation />
                <div className="page-title add-lessons-header">
                    <Header headerName="Add Lessons" />
                    <button className="add-lesson-button" onClick={addLesson}>
                        <img src={plusIcon} className="plus-sign" alt="plus sign" />
                    </button>
                </div>
                {lessons.map((lesson, index) => (
                    <div key={index}>{lesson}</div>
                ))}
            </div>
            <div className="create-course-button-container">
                <button className="create-course-button">Create Course</button>
            </div>
        </div>
    );
}

