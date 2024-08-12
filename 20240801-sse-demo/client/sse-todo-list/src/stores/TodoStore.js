import Store from './Store';
import { apiService } from '../service/ApiService';

export default class TodoStore extends Store {
  constructor() {
    super();
    this.todos = [];
    this.text = '';
  }

  async fetchTodos() {
    const todos = await apiService.fetchTodos();
    this.todos = todos;
    this.publish();
  }

  async addTodo() {
    if (!this.text.trim()) return;
    await apiService.addTodo({ text: this.text });
    this.text = '';
    this.publish();
    // 새 todo는 SSE를 통해 추가될 것입니다.
  }

  addTodoFromSSE(newTodo) {
    console.log(newTodo);
    this.todos = [...this.todos, newTodo];
    this.publish();
  }

  setText(text) {
    this.text = text;
    this.publish();
  }
}

export const todoStore = new TodoStore();
