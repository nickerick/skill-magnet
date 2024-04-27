class CourseService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api/';

  async getCourse(courseId) {
    const response = await fetch(`${this.baseUrl}course/${courseId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }

  async getAllCourses() {
    const response = await fetch(`${this.baseUrl}course`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }

  async getEnrolledCourses(userId) {
    const response = await fetch(`${this.baseUrl}enrolls/user/${userId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }

  async createCourse(course){
    const response = await fetch(`${this.baseUrl}course`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(course)
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }

}

export default new CourseService();
