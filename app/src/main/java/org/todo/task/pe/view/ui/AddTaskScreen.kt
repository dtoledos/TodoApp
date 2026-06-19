package org.todo.task.pe.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.todo.task.pe.R
import org.todo.task.pe.view.model.AddTaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onBack: () -> Unit,
    viewModel: AddTaskViewModel = viewModel()
) {
    val taskName by viewModel.taskName.collectAsState()
    val description by viewModel.description.collectAsState()
    val priority by viewModel.priority.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.CropSquare, contentDescription = stringResource(R.string.cd_close_screen))
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.saveTask()
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.cd_save_task),
                            tint = Color(0xFF64B5F6) // Color azul del check en tu imagen
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF121212)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF121212)) // Fondo oscuro profundo
                .verticalScroll(rememberScrollState())
        ) {
            // Campo: Nombre de la Tarea
            TextField(
                value = taskName,
                onValueChange = { viewModel.onTaskNameChange(it) },
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_task_name),
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    // Colores del fondo (contenedor)
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,

                    // Colores de la línea inferior (indicador)
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    // Colores del texto
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )

            HorizontalDivider(color = Color.DarkGray, thickness = 0.5.dp)

            // Filas de opciones
            TaskOptionRow(Icons.Outlined.Event, stringResource(R.string.label_no_start_date))
            TaskOptionRow(Icons.Outlined.AccessTime, stringResource(R.string.label_no_due_date))
            TaskOptionRow(Icons.Outlined.Repeat, stringResource(R.string.label_no_repeat))

            // Fila Especial: Prioridad con círculos
            PriorityRow(selectedPriority = priority, onPriorityClick = { viewModel.onPriorityChange(it) })

            TaskOptionRow(Icons.Outlined.List, stringResource(R.string.label_default_list), hasBox = true)
            TaskOptionRow(Icons.Outlined.Label, stringResource(R.string.label_add_labels))
            TaskOptionRow(Icons.Outlined.SubdirectoryArrowRight, stringResource(R.string.label_add_subtask))

            HorizontalDivider(color = Color.DarkGray, thickness = 0.5.dp, modifier = Modifier.padding(vertical = 8.dp))

            // Campo: Descripción
            TaskOptionRow(Icons.Outlined.Subject, stringResource(R.string.label_description))
        }
    }
}

@Composable
fun TaskOptionRow(icon: ImageVector, label: String, hasBox: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Esto desactiva el ripple conflictivo
            ) { /* Acción vacía */ }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(32.dp))

        if (hasBox) {
            Surface(
                color = Color.DarkGray,
                shape = CircleShape,
                modifier = Modifier.height(32.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp), // Aquí está la corrección
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(label, color = Color.White, fontSize = 14.sp)
                }
            }
        } else {
            Text(label, color = Color.Gray, fontSize = 16.sp)
        }
    }
    HorizontalDivider(color = Color(0xFF222222), thickness = 0.5.dp)
}

@Composable
fun PriorityRow(selectedPriority: Int, onPriorityClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.Flag, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(32.dp))
        Text(stringResource(R.string.label_priority), color = Color.Gray, modifier = Modifier.weight(1f))

        // Círculos de colores
        val colors = listOf(Color.White, Color.Blue, Color.Yellow, Color.Red)
        colors.forEachIndexed { index, color ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
                    .background(if (selectedPriority == index) color.copy(alpha = 0.2f) else Color.Transparent, CircleShape)
                    .border(2.dp, color, CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // Esto desactiva el ripple conflictivo
                    ) {  onPriorityClick(index) }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
    HorizontalDivider(color = Color(0xFF222222), thickness = 0.5.dp)
}