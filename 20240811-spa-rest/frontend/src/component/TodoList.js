import React, { useState, useEffect } from 'react';

function TodoList() {
    const [todos, setTodos] = useState([]);
    const [newTodo, setNewTodo] = useState('');

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        const response = await fetch('/api/todos');
        const data = await response.json();
        setTodos(data);
    };

    const addTodo = async () => {
        const response = await fetch('/api/todos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ text: newTodo, completed: false })
        });
        const todo = await response.json();
        setTodos([...todos, todo]);
        setNewTodo('');
    };

    const toggleTodo = async (id) => {
        const todo = todos.find(t => t.id === id);
        const response = await fetch(`/api/todos/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ ...todo, completed: !todo.completed })
        });
        const updatedTodo = await response.json();
        setTodos(todos.map(t => t.id === id ? updatedTodo : t));
    };

    const deleteTodo = async (id) => {
        await fetch(`/api/todos/${id}`, { method: 'DELETE' });
        setTodos(todos.filter(t => t.id !== id));
    };

    return (
        <div>
            <input
                type="text"
                value={newTodo}
                onChange={(e) => setNewTodo(e.target.value)}
            />
            <button onClick={addTodo}>Add Todo</button>
            <ul>
                {todos.map(todo => (
                    <li key={todo.id}>
                        <input
                            type="checkbox"
                            checked={todo.completed}
                            onChange={() => toggleTodo(todo.id)}
                        />
                        {todo.text}
                        <button onClick={() => deleteTodo(todo.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default TodoList;
