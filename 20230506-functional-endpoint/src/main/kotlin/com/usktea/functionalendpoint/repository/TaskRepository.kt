package com.usktea.functionalendpoint.repository

import com.usktea.functionalendpoint.models.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long>