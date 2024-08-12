import React from 'react';
import useTodoStore from './hooks/useTodoStore';

function TodoForm() {
  const todoStore = useTodoStore();

  const handleSubmit = async (e) => {
    e.preventDefault();
    await todoStore.addTodo();
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={todoStore.text}
        onChange={(e) => todoStore.setText(e.target.value)}
        placeholder="Enter new todo"
      />
      <button type="submit">Add Todo</button>
    </form>
  );
}

export default TodoForm;
