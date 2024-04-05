import { useEffect, useState } from 'react';
import MyCourseCard from '../../components/mycoursescard/MyCourseCard.jsx';
import './MyCourses.css';
import CourseService from '../../services/CourseService';

export default function MyCourses() {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    const fetchEnrolledCourses = async () => {
      try {
        const coursesData = await CourseService.getEnrolledCourses(1);
        console.log(coursesData);
        setCourses(coursesData);
      } catch (error) {
        alert('ERROR: Failed to fetch enrolled courses');
      }
    };

    fetchEnrolledCourses();
  }, []);

  return (
    <div className="my-courses">
      <div className="enrolled-courses-title">Enrolled Courses</div>

      <div className="mycourses-list-container">
        <div className="mycourses-list">
          {courses.map(c => (
            <MyCourseCard
              key={c.enrolledCourse.id}
              courseTitle={c.enrolledCourse.title}
              imageUrl=""
              instructorName={c.enrolledCourse.description}
              progress={c.progress}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
