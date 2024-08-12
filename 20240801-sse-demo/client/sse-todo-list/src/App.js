import React, { useEffect } from 'react';
import TodoList from './TodoList';
import TodoForm from './TodoForm';
import { todoStore } from './stores/TodoStore';
import './App.css';

export const randomId =
  Math.random().toString(36).substring(2, 15) +
  Math.random().toString(36).substring(2, 15);

function App() {
  const eventSource = new EventSource(
    `http://localhost:8080/sse?id=${randomId}`,
  );

  eventSource.onopen = (event) => {
    console.log('SSE connection opened:', event);
  };

  eventSource.addEventListener('taskUpdated', (event) => {
    try {
      const newTodo = JSON.parse(event.data);
      console.log('Parsed todo:', newTodo);
      todoStore.addTodoFromSSE(newTodo);
    } catch (error) {
      console.error('Error parsing taskUpdated event data:', error);
    }
  });

  eventSource.onerror = (error) => {
    console.error('SSE Error:', error);
  };

  return (
    <div className="App">
      <h1>SSE Todo List</h1>
      <TodoForm />
      <TodoList />
    </div>
  );
}

export default App;
