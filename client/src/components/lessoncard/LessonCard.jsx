import { useState } from 'react';
import './LessonCard.css';

export default function LessonCard({ lessonTitle, imageUrl, completionStatus, index }) {
  const [selectedCardIndex, setSelectedCardIndex] = useState(null);

  const handleClick = () => {
    setSelectedCardIndex(index === selectedCardIndex ? null : index);
  };

  return (
    <div onClick={handleClick} className={`lesson-card ${index === selectedCardIndex ? 'clicked' : ''}`}>
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