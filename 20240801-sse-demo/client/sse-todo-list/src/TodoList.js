import React, { useEffect } from 'react';
import useTodoStore from './hooks/useTodoStore';

function TodoList() {
  const todoStore = useTodoStore();

  useEffect(() => {
    todoStore.fetchTodos();
  }, [todoStore]);

  return (
    <ul>
      {todoStore.todos.map((todo) => {
        console.log(todo);
        return <li key={todo.id}>{todo.title}</li>;
      })}
    </ul>
  );
}

export default TodoList;
