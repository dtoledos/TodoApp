package org.todo.task.pe.view.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddTaskViewModel : ViewModel() {
    // Estados de la UI
    private val _taskName = MutableStateFlow("")
    val taskName = _taskName.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _priority = MutableStateFlow(0) // 0: Ninguna, 1: Azul, 2: Amarillo, 3: Rojo
    val priority = _priority.asStateFlow()

    // Funciones para actualizar el estado
    fun onTaskNameChange(newName: String) { _taskName.value = newName }
    fun onDescriptionChange(newDesc: String) { _description.value = newDesc }
    fun onPriorityChange(newPriority: Int) { _priority.value = newPriority }

    fun saveTask() {
        val name = _taskName.value
        if (name.isNotBlank()) {
            // Aquí llamarás al Repository para guardar en Room más adelante
            println("Tarea guardada: $name")
        }
    }
}