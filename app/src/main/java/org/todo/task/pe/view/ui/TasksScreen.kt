package org.todo.task.pe.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.todo.task.pe.view.model.TasksViewModel
import org.todo.task.pe.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    onNavigateToAdd: () -> Unit,
    viewModel: TasksViewModel = viewModel()
) {
    // Observamos el estado de las tareas
    val tasks by viewModel.tasksState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.title_my_tasks)) },
                actions = {
                    IconButton(onClick = { /* Acción de configuración */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(id = R.string.cd_settings)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* Menú */ }) {
                        Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.cd_menu))
                    }
                    IconButton(onClick = { /* Buscar */ }) {
                        Icon(Icons.Default.Search, contentDescription = stringResource(R.string.cd_search))
                    }
                    IconButton(onClick = { /* Ordenar */ }) {
                        Icon(Icons.Default.SwapVert, contentDescription = stringResource(R.string.cd_sort))
                    }
                    IconButton(onClick = { /* Voz */ }) {
                        Icon(Icons.Default.Mic, contentDescription = stringResource(R.string.cd_voice_input))
                    }
                    IconButton(onClick = { /* Más opciones */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = stringResource(R.string.cd_more_options))
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onNavigateToAdd,
                        containerColor = MaterialTheme.colorScheme.primaryContainer // Color azul claro de la imagen
                    ) {
                        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.cd_add_task))
                    }
                },
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // 1. EL BANNER VA PRIMERO:
            // Aparecerá justo debajo de tu TopAppBar ("Mis Tareas")
            BannerAd()

            // 2. EL CONTENEDOR DE LA LISTA VA DESPUÉS:
            // Con weight(1f) ocupará todo el resto del espacio hasta abajo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                if (tasks.isEmpty()) {
                    EmptyTasksState()
                } else {
                    // Aquí irá el LazyColumn de Room
                    Text("Lista de tareas...")
                }
            }
        }
    }
}

@Composable
fun EmptyTasksState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Nota: Estoy usando un ícono por defecto (Inbox). Para que sea exacto a tu imagen,
        // deberías importar ese SVG específico a tus res/drawable y usar painterResource(id = R.drawable.tu_icono)
        Icon(
            imageVector = Icons.Outlined.Inbox,
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            tint = Color.Gray // O MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.empty_tasks_message),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.LightGray // O MaterialTheme.colorScheme.onSurface
        )
    }
}