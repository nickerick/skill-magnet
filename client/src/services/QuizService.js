class UserService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api';

  async getQuiz(quizId) {
    const response = await fetch(`${this.baseUrl}quiz/${quizId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch Failed');

    return await response.json();
  }

  async getQuizzesByLesson(lessonId) {
    const response = await fetch(`${this.baseUrl}quiz/lesson/${lessonId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch Failed');
    return await response.json();
  }

  async getQuizzesByCourse(courseId) {
    const response = await fetch(`${this.baseUrl}quiz/course/${lessonId}`, {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' },
    });

    if (!response.ok) throw new Error('Fetch Failed');
    return await response.json();
  }

  async submitQuizResponses(answers) {
    const response = await fetch(`${this.baseUrl}results/submit`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(answers),
    });

    if (!response.ok) throw new Error('Fetch Failed');

    return await response.json();
  }

  async createQuiz(title, description, lessonId) {
    const quizBody = {
      title: title,
      description: description,
      lessonId: lessonId,
    };

    const response = await fetch(`${this.baseUrl}results/submit`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(quizBody),
    });
    return await response.json();
  }
}

export default new UserService();
