import MyCourseCard from '../../components/mycoursescard/MyCourseCard.jsx';
import './MyCourses.css'

export default function MyCourses() {
  return(
    <div className="my-courses">
      <div className="enrolled-courses-title">Enrolled Courses</div>

      <div className="mycourses-list-container">
        <div className="mycourses-list">
          <MyCourseCard courseTitle="Course 1" imageUrl="" instructorName="Instructor 1" progress={100}/>
          <MyCourseCard courseTitle="Course 2" imageUrl="" instructorName="Instructor 2" progress={75}/>
          <MyCourseCard courseTitle="Course 3" imageUrl="" instructorName="Instructor 3" progress={50}/>
          <MyCourseCard courseTitle="Course 4" imageUrl="" instructorName="Instructor 4" progress={30}/>
          <MyCourseCard courseTitle="Course 5" imageUrl="" instructorName="Instructor 5" progress={0}/>
          <MyCourseCard courseTitle="Course 6" imageUrl="" instructorName="Instructor 6" progress={10}/>

        </div>
      </div>

    </div>
  );
}