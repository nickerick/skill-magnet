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

  async submitQuizResponses(answers) {
    const response = await fetch(`${this.baseUrl}results/submit`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(answers),
    });

    if (!response.ok) throw new Error('Fetch Failed');

    return await response.json();
  }
}

export default new UserService();
