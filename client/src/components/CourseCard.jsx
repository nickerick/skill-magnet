
import './CourseCard.css';

export default function CourseCard({courseTitle, imageUrl, instructorName}) {
  return (
    <div className="course-card">
      <div className="course-image">
        <img src={imageUrl} />
      </div>
      <div className="course-title">
        {courseTitle}
      </div>
      <div className="course-instructor">
        {instructorName}
      </div>
      <button className="enroll-button">Enroll Now</button>
</div>
  );}
