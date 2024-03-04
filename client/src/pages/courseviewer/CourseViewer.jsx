import React, { useState } from 'react';
import LessonCard from '../../components/lessoncard/LessonCard.jsx';
import VideoService from '../../services/VideoService.js';
import './CourseViewer.css';

export default function CourseViewer() {
  const [progress, setProgress] = useState(0);
  const [videoUrl, setVideoUrl] = useState('');
  const [selectedLesson, setSelectedLesson] = useState(null);

  const loadVideo = videoName => {
    var videoUrl = VideoService.getVideoUrl(videoName);
    setVideoUrl(videoUrl);
  };

  const handleButtonClick = ()=>{
    if(progress < 100){
      setProgress(progress + 20);
    }
  }
  const handleButtonReset = ()=>{
    setProgress(0);
  }

  const handleLessonClick = lessonTitle => {
    setSelectedLesson(lessonTitle);
    loadVideo(lessonTitle);
  }

  return (
    <div className="course-view">

      <div className="progress-title-bar">
        <div className="progress-title">Course Progress</div>

        <div className="course-progress">
          <div className="progress-bar">
            <div className="progress-bar-fill" style={{ width: `${progress}%` }}></div>
          </div>

          {/*These buttons are temporary to be able to check progress bar*/}
          <div className="button-container">
            <button onClick={handleButtonClick}>temp progress button</button>
            <button onClick={handleButtonReset}>temp reset button</button>
          </div>
        </div>

        <div className="progress-label">{progress}%</div>

      </div>

      <div className="lessons-and-video">
        <div className="lessons-list">
          <LessonCard
            lessonTitle="Intro to Web Programming 1"
            completionStatus="[Progress %]"
            isSelected={selectedLesson === "Intro to Web Programming 1"}
            onClick={() => handleLessonClick("Intro to Web Programming 1")}
          />
          <LessonCard
            lessonTitle="Intro to Web Programming 2"
            completionStatus="[Progress %]"
            isSelected={selectedLesson === "Intro to Web Programming 2"}
            onClick={() => handleLessonClick("Intro to Web Programming 2")}
          />
          <LessonCard
            lessonTitle="Intro to Web Programming 3"
            completionStatus="[Progress %]"
            isSelected={selectedLesson === "Intro to Web Programming 3"}
            onClick={() => handleLessonClick("Intro to Web Programming 3")}
          />
          <LessonCard
            lessonTitle="Intro to Web Programming 4"
            completionStatus="[Progress %]"
            isSelected={selectedLesson === "Intro to Web Programming 4"}
            onClick={() => handleLessonClick("Intro to Web Programming 4")}
          />
        </div>

        <div className="video-player">
          <video src={videoUrl} width="1100" height="619" controls></video>
        </div>
      </div>

    </div>
  );
}
