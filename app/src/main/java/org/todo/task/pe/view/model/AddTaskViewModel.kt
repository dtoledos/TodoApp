package org.todo.task.pe.view.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddTaskViewModel : ViewModel() {
    // Estados de la UI
    // Estado para la fecha seleccionada (nulo significa "Sin fecha")
    private val _startDate = MutableStateFlow<Long?>(null)
    val startDate = _startDate.asStateFlow()

    // Estado para controlar la visibilidad del DatePicker
    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker = _showDatePicker.asStateFlow()

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

    fun onStartDateSelected(dateMillis: Long?) {
        _startDate.value = dateMillis
    }

    fun setShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }
}