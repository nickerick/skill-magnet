import './MyCourseCard.css';

export default function MyCourseCard({
  courseTitle,
  imageUrl,
  description,
  progress,
  handleClick,
}) {
  return (
    <div onClick={handleClick} className="my-course-card">
      <div className="my-course-image">
        <img src={imageUrl} />
      </div>
      <div className="my-course-title">{courseTitle}</div>
      <div className="my-course-instructor">{description}</div>

      <div className="my-course-progress-bar">
        <div
          className="my-course-progress-bar-fill"
          style={{ width: `${progress}%` }}
        ></div>
      </div>
      <div className="my-course-progress-label">{progress}%</div>
    </div>
  );
}
