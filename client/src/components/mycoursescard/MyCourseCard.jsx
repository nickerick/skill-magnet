import './MyCourseCard.css';

export default function MyCourseCard({courseTitle, imageUrl, instructorName, progress}) {
  return (
    <div className="my-course-card">
      <div className="my-course-image">
        <img src={imageUrl} />
      </div>
      <div className="my-course-title">
        {courseTitle}
      </div>
      <div className="my-course-instructor">
        {instructorName}
      </div>

      <div className="progress-bar">
        <div className="progress-bar-fill" style={{ width: `${progress}%` }}></div>
      </div>
      <div className="progress-label">{progress}%</div>


    </div>
  );
}
