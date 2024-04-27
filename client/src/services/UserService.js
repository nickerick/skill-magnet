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

  async loginUser(username, passwordHash) {
    try {
      const response = await fetch(`${this.baseUrl}user/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, passwordHash }),
      });

      if (response.ok) {
        return await response.json();
      } else if (response.status === 401) {
        throw new Error('Unauthorized access');
      } else {
        throw new Error('Failed to log in');
      }
    } catch (error) {
      console.error('ERROR: Error logging in');
      throw error;
    }
  }
  async signupUser(username, passwordHash) {
    try {
      const response = await fetch(`${this.baseUrl}user/create`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, passwordHash }),
      });

      if (response.ok) {
        return await response.json();
      } else if (response.status === 400) {
        throw new Error('Duplicate Username');
      } else {
        throw new Error('Failed to sign up');
      }
    } catch (error) {
      console.error('ERROR: Error signing up');
      throw error;
    }
  }
}

export default new UserService();
