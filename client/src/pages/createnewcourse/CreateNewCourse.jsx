import React, { useState } from 'react';
import Header from "../../components/Header";
import BasicCourseInformation from "../../components/courseinformation/BasicCourseInformation";
import BasicLessonInformation from "../../components/courseinformation/BasicLessonInformation";
import plusIcon from "../../assets/math-plus-icon.svg";
import "./CreateNewCourse.css";

export default function CreateNewCourse() {
    const [courseInfo, setCourseInfo] = useState({
        lessons: [],
        title: '',
        description: '',
        category: '',
    });

    const [lessons, setLessons] = useState([{ id: 0, title: '', selectedFile: null, videoLink: '' }]);

    const addLesson = () => {
        const newId = lessons.length;
        setLessons([...lessons, {
            id: newId,
            title: '',
            selectedFile: null,
            videoLink: '' }]);
    };

    const handleLessonInfoChange = (lessonId, lessonInfo) => {
        setLessons(prevLessons => {
            const updatedLessons = prevLessons.map(lesson => {
                if (lesson.id === lessonId) {
                    return { ...lesson, ...lessonInfo };
                }
                return lesson;
            });
            return updatedLessons;
        });
    };

    const handleFileInputChange = (e, lessonId) => {
        const file = e.target.files[0];
        const newLessons = lessons.map(lesson => {
            if (lesson.id === lessonId) {
                return {
                    ...lesson,
                    selectedFile: file,
                    videoLink: file ? file.name : ''
                };
            }
            return lesson;
        });
        setLessons(newLessons);
    };

    const handleTitleChange = (e, lessonId) => {
        const newTitle = e.target.value;
        const newLessons = lessons.map(lesson => {
            if (lesson.id === lessonId) {
                return {
                    ...lesson,
                    title: newTitle
                };
            }
            return lesson;
        });
        setLessons(newLessons);
    };



    const handleCreateCourse = () => {
       //testing
        //const lessonTitles = lessons.map(lesson => lesson.title);
        //const lessonFiles = lessons.map(lesson => lesson.selectedFile);

        const updatedCourseInfo = {
            ...courseInfo,
            lessons: lessons.map(lesson => ({
                title: lesson.title,
                videoLink: lesson.videoLink
            }))
        };

        console.log(updatedCourseInfo);
        // console.log('Lesson Titles:', lessonTitles);
        // console.log('Lesson Video Files:', lessonFiles);
    };


    return (
      <div>
          <div className="create-course">
              <div className="page-title">
                  <Header headerName="Course Information" />
              </div>
              <BasicCourseInformation
                setCourseInfo={setCourseInfo}
                courseInfo={courseInfo}
              />
              <div className="page-title add-lessons-header">
                  <Header headerName="Add Lessons" />
                  <button className="add-lesson-button" onClick={addLesson}>
                      <img src={plusIcon} className="plus-sign" alt="plus sign" />
                  </button>
              </div>
              {lessons.map((lesson, index) => (
                <div key={index}>
                    <BasicLessonInformation
                      lessonId={lesson.id}
                      handleLessonInfoChange={handleLessonInfoChange}
                      handleFileInputChange={handleFileInputChange}
                    />

                </div>
              ))}
          </div>
          <div className="create-course-button-container">
              <button className="create-course-button" onClick={handleCreateCourse}>Create Course</button>
          </div>
      </div>
    );
}
