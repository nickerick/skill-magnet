import React, { useState } from 'react';
import LessonCard from '../../components/lessoncard/LessonCard.jsx';
import VideoService from '../../services/VideoService.js';
import './CourseViewer.css';

export default function CourseViewer() {
  const [progress, setProgress] = useState(0);
  const [videoUrl, setVideoUrl] = useState('');
  const [selectedLesson, setSelectedLesson] = useState(null);

  const handleLessonSelect = (lessonIndex) => {
    setSelectedLesson(lessonIndex === selectedLesson ? null : lessonIndex);
  };
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

  return (
    <div className="course-view">
      <div className="progress-title">Course Progress</div>

      <div className="course-progress">
        <div className="progress-bar">
          <div className="progress-bar-fill" style={{ width: `${progress}%` }}></div>
        </div>
        <div className="progress-label">{progress}%</div>

        {/*These buttons are temporary to be able to check progress bar*/}
        <button onClick={handleButtonClick}>temp progress button</button>
        <button onClick={handleButtonReset}>temp reset button</button>
      </div>

      <div className="lessons-list">
        <LessonCard
          lessonTitle="[Lesson 1 title here]"
          completionStatus="[% here]"
          isSelected={selectedLesson === 0}
          onSelect={() => handleLessonSelect(0)}
        />
        <LessonCard
          lessonTitle="[Lesson 2 title here]"
          completionStatus="[% here]"
          isSelected={selectedLesson === 1}
          onSelect={() => handleLessonSelect(1)}
        />
        <LessonCard
          lessonTitle="[Lesson 3 title here]"
          completionStatus="[% here]"
          isSelected={selectedLesson === 2}
          onSelect={() => handleLessonSelect(2)}
        />
        <LessonCard
          lessonTitle="[Lesson 3 title here]"
          completionStatus="[% here]"
          isSelected={selectedLesson === 3}
          onSelect={() => handleLessonSelect(3)}
        />
      </div>

      <div className="video-player">
        {selectedLesson !== null && (
          <video src={videoUrl} width="750" height="500" controls></video>
        )}
      </div>
    </div>
  );
}
