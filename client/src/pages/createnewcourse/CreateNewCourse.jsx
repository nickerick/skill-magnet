import React, { useState } from 'react';
import Header from "../../components/Header";
import BasicCourseInformation from "../../components/courseinformation/BasicCourseInformation";
import BasicLessonInformation from "../../components/courseinformation/BasicLessonInformation";
import plusIcon from "../../assets/math-plus-icon.svg";
import "./CreateNewCourse.css";
import CourseService from '../../services/CourseService.js';
import videoService from '../../services/VideoService';
import LessonService from '../../services/LessonService.js';

export default function CreateNewCourse() {
    const [uploading, setUploading] = useState(false);

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

    const handleCreateCourse = async () => {

        setUploading(true);

        try {
            const uploadPromises = lessons.map(lesson => videoService.uploadVideo(lesson.selectedFile, lesson.title));

            const uploadResults = await Promise.all(uploadPromises);

            const videoUrls = uploadResults.map(result => videoService.getVideoUrl(result));

            const coursePayload = {
                title: courseInfo.title,
                description: courseInfo.description,
                category: courseInfo.category
            };

            const createdCourse = await CourseService.createCourse(coursePayload);

            const lessonPromises = lessons.map((lesson, index) => {
                const lessonPayload = {
                    title: lesson.title,
                    courseId: createdCourse.id,
                    videoLink: videoUrls[index],
                    videoType: "SELF_HOSTED",
                    videoNumber: index + 1
                };
                return LessonService.createLesson(lessonPayload);
            });

            await Promise.all(lessonPromises);
            setUploading(false);
            console.log("Course creation successful!");
            window.location.reload()

        } catch (error) {
            console.error("Error creating course or lessons:", error);
            setUploading(false);
        }
    };

    return (
      <div>
          <div className={`upload-modal ${uploading ? 'show-modal' : ''}`}>
              <div className="upload-modal-content">
                  <h2>Uploading Course...</h2>
              </div>
          </div>


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
