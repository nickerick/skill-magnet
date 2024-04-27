import './CourseCard.css';
import { useState } from 'react';
import EnrollsService from '../services/EnrollsService.js';

export default function CourseCard({ courseId, courseTitle, imageUrl, description }) {
  const [showPopup, setShowPopup] = useState(false);
  const [showEnrollSuccess, setShowEnrollSuccess] = useState(false);
  const [showAlreadyEnrolled, setShowAlreadyEnrolled] = useState(false);


  const handleEnrollClick = () => {
    setShowPopup(true);
  };

  const handleEnrollConfirm = async () => {
    setShowPopup(false);
    try {
      const isEnrolled = await EnrollsService.checkEnrollment(courseId, 1);
      if (!isEnrolled) {
        const user_course = {
          courseId: courseId,
          userId: 1 //hardcoded user id
        };
        console.log(user_course);
        await EnrollsService.enrollUser(user_course);
        setShowEnrollSuccess(true);
        setTimeout(() => {
          setShowEnrollSuccess(false);
        }, 1250);
      }
      else {
        setShowAlreadyEnrolled(true);
        setTimeout(() => {
          setShowAlreadyEnrolled(false);
        }, 1250);
      }
    } catch (error) {
      alert('ERROR: Failed to enroll user');
    }
  };


  const handleEnrollCancel = () => {
    setShowPopup(false);
  };

  return (
    <div className="course-card">
      <div className="course-image">
        <img src={imageUrl} />
      </div>
      <div className="course-title">
        {courseTitle}
      </div>
      <div className="course-description">
        {description}
      </div>
      <button className="enroll-button" onClick={handleEnrollClick}>Enroll Now</button>

      {showPopup && (
        <div className="popup-background">
          <div className="popup-content">
            <p>Enroll in {courseTitle}?</p>
            <div>
              <button onClick={handleEnrollConfirm}>Yes</button>
              <button onClick={handleEnrollCancel}>No</button>
            </div>
          </div>
        </div>
      )}

      {showEnrollSuccess && (
        <div className="upload-success-popup show-popup">
          <h2>Successfully enrolled in course.</h2>
        </div>
      )}

      {showAlreadyEnrolled && (
        <div className="enroll-fail-popup show-popup">
          <h2>You are already enrolled in this course.</h2>
        </div>
      )}

    </div>
  );
}
