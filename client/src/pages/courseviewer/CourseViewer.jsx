import './CourseViewer.css';
import { useParams } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import LessonCard from '../../components/lessoncard/LessonCard.jsx';
import VideoService from '../../services/VideoService.js';
import CourseService from '../../services/CourseService';
import QuizzesAvailable from '../../components/quiz/QuizzesAvailable.jsx';
import { Box, Container } from '@mui/material';
export default function CourseViewer() {
  let { courseId } = useParams();
  const [course, setCourse] = useState(null);
  const [selectedLessonNum, selectLessonNum] = useState(1);
  const [videoUrl, setVideoUrl] = useState('');

  useEffect(() => {
    const fetchEnrolledCourses = async () => {
      try {
        const courseData = await CourseService.getCourse(courseId);
        setCourse(courseData);
        loadVideo(courseData?.id, courseData?.lessons[0]?.id);
      } catch (error) {
        alert('ERROR: Failed to fetch current course');
      }
    };

    fetchEnrolledCourses();
  }, []);

  const loadVideo = (courseId, lessonId) => {
    var videoName = `c${courseId}.l${lessonId}.mp4`;
    var videoUrl = VideoService.getVideoUrl(videoName);
    setVideoUrl(videoUrl);
  };

  const handleSelectLesson = lesson => {
    selectLessonNum(lesson.videoNumber);
    loadVideo(course.id, lesson.id);
  };

  return (
    <div className="course-view">
      <div className="progress-title-bar">
        <div className="progress-title">Course Progress</div>

        <div className="course-progress">
          <div className="progress-bar">
            <div
              className="progress-bar-fill"
              style={{ width: `${course?.progress ?? 0}%` }}
            ></div>
          </div>
        </div>

        <div className="progress-label">{course?.progress ?? 0}%</div>
      </div>

      <div className="lessons-and-video">
        <div className="lessons-list">
          {course?.lessons?.map(lesson => (
            <LessonCard
              lessonTitle={lesson.title}
              completionStatus="" // We only have course completion on courses rather than lessons
              isSelected={lesson.videoNumber === selectedLessonNum}
              onClick={() => handleSelectLesson(lesson)}
            />
          ))}
        </div>

        <div className="video-player">
          <video src={videoUrl} width="1100" height="619" controls></video>
        </div>
      </div>

      {course !== null && (
        <QuizzesAvailable
          lessonId={course?.lessons[selectedLessonNum - 1]?.id}
        />
      )}
    </div>
  );
}
