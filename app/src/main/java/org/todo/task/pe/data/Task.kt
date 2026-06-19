package org.todo.task.pe.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
}

class MockTaskRepository : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return flowOf(emptyList())
    }
}