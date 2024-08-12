import axios from 'axios';
import { randomId } from '../App';

const baseUrl = 'http://localhost:8080';

class ApiService {
  constructor() {
    this.instance = axios.create({
      baseURL: baseUrl,
    });
  }

  async fetchTodos() {
    const { data } = await this.instance.get('/todos');
    return data;
  }

  async addTodo(todo) {
    const { data } = await this.instance.post(`/todos?id=${randomId}`, todo);
    return data;
  }
}

export const apiService = new ApiService();
