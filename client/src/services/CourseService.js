class CourseService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api/';

  async getAllCourses() {
    const response = await fetch(`${this.baseUrl}course`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }
}

export default new CourseService();
