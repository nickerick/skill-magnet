import MyCourseCard from '../../components/mycoursescard/MyCourseCard.jsx';
import './MyCourses.css'

export default function MyCourses() {
  return(
    <div className="my-courses">
      <div className="enrolled-courses-title">Enrolled Courses</div>

      <div className="mycourses-list-container">
        <div className="mycourses-list">
          <MyCourseCard courseTitle="Course 1" imageUrl="" instructorName="Instructor 1"  />
          <MyCourseCard courseTitle="Course 2" imageUrl="" instructorName="Instructor 2"  />
          <MyCourseCard courseTitle="Course 3" imageUrl="" instructorName="Instructor 3" />
          <MyCourseCard courseTitle="Course 4" imageUrl="" instructorName="Instructor 4"  />
          <MyCourseCard courseTitle="Course 5" imageUrl="" instructorName="Instructor 5"  />
          <MyCourseCard courseTitle="Course 6" imageUrl="" instructorName="Instructor 6" />
          <MyCourseCard courseTitle="Course 7" imageUrl="" instructorName="Instructor 7"  />
          <MyCourseCard courseTitle="Course 8" imageUrl="" instructorName="Instructor 8"  />
          <MyCourseCard courseTitle="Course 9" imageUrl="" instructorName="Instructor 9" />
        </div>
      </div>

    </div>
  );
}