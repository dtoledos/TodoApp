package org.todo.task.pe.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.todo.task.pe.data.MockTaskRepository
import org.todo.task.pe.data.Task
import org.todo.task.pe.data.TaskRepository

class TasksViewModel(
    private val repository: TaskRepository = MockTaskRepository() // Inyección manual temporal
) : ViewModel() {

    // Expone el flujo de datos como un estado para Compose
    val tasksState: StateFlow<List<Task>> = repository.getTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}