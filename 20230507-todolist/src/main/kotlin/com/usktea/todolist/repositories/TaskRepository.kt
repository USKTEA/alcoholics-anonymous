package com.usktea.todolist.repositories

import com.usktea.todolist.models.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>
