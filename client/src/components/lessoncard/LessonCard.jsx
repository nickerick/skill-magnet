import { useState } from 'react';
import './LessonCard.css';

export default function LessonCard({ lessonTitle, imageUrl, completionStatus, isSelected, onClick }) {

  return (
    <div
      className={`lesson-card ${isSelected ? 'selected' : ''}`}
      onClick={onClick}
    >
      <div className="lesson-image">
        <img src={imageUrl} alt="Lesson" />
      </div>
      <div className="lesson-details">
        <div className="lesson-title">{lessonTitle}</div>
        <div className="lesson-completion-status">{completionStatus}</div>
      </div>
    </div>
  );
}