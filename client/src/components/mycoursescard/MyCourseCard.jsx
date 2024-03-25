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




    </div>
  );}
