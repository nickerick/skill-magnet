class UserService {
  baseUrl = import.meta.env.VITE_API_URL_BASE ?? '/api/';

  async getUser(username) {
    try {
      const response = await fetch(`${this.baseUrl}user?username=${username}`, {
        method: 'GET',

        headers: { 'Content-Type': 'application/json' },
      });

      if (!response.ok) throw new Error('Fetch failed');

      return await response.json();
    } catch (err) {
      return null;
    }
  }
}

export default new UserService();
