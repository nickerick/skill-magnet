class LessonService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api/';

  async createLesson(lesson){
    const response = await fetch(`${this.baseUrl}lessons`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(lesson)
    });

    if (!response.ok) throw new Error('Fetch failed');

    return await response.json();
  }

}

export default new LessonService();
