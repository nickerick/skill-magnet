import React, { useState } from 'react';
import LessonCard from '../../components/lessoncard/LessonCard.jsx';
import VideoService from '../../services/VideoService.js';

export default function CourseViewer() {
  const [videoUrl, setVideoUrl] = useState('');
  const [selectedLesson, setSelectedLesson] = useState(null);

  const handleLessonSelect = (lessonIndex) => {
    setSelectedLesson(lessonIndex === selectedLesson ? null : lessonIndex);
  };
  const loadVideo = videoName => {
    var videoUrl = VideoService.getVideoUrl(videoName);
    setVideoUrl(videoUrl);
  };
  return (
    <div className="lessons-list-container">
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
      </div>
      <div className="video-player">
        {selectedLesson !== null && (
          <video src={videoUrl} width="750" height="500" controls></video>
        )}
      </div>
    </div>
  );
}
