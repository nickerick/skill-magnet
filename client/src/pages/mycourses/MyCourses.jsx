import { useEffect, useState } from 'react';
import MyCourseCard from '../../components/mycoursescard/MyCourseCard.jsx';
import Header from '../../components/Header.jsx';
import './MyCourses.css';
import CourseService from '../../services/CourseService';
import { useNavigate } from 'react-router-dom';
import { useUser } from '/src/UserContext.jsx';

export default function MyCourses() {
  const navigate = useNavigate();
  const [courses, setCourses] = useState([]);
  const { userId } = useUser();

  useEffect(() => {
    const fetchEnrolledCourses = async () => {
      try {
        const coursesData = await CourseService.getEnrolledCourses(1); //hardwired for demo
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
      <div className="enrolled-courses-title"><Header headerName="Enrolled Courses" /></div>

      <div className="mycourses-list-container">
        <div className="mycourses-list">
          {courses.map(c => (
            <MyCourseCard
              key={c.enrolledCourse.id}
              courseTitle={c.enrolledCourse.title}
              imageUrl={`/images/courses/${c.enrolledCourse.id}/courseThumbnail.png`}
              description={c.enrolledCourse.description}
              progress={c.progress}
              handleClick={() => navigate(`/courseviewer/${c.id}`)}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
