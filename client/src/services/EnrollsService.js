class EnrollsService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api/';

  async enrollUser(user_course) {
    const response = await fetch(`${this.baseUrl}enrolls`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user_course),
    });

    return await response.json();
  }

  async checkEnrollment(courseId, userId) {
    try {
      const response = await fetch(`${this.baseUrl}enrolls/user/${userId}`);
      const enrollments = await response.json();

      const isEnrolled = enrollments.some(enrollment => enrollment.enrolledCourse.id === courseId);

      return isEnrolled;
    } catch (error) {
      console.error('ERROR: Error checking enrollments');
      throw error;
    }
  }
}

export default new EnrollsService();