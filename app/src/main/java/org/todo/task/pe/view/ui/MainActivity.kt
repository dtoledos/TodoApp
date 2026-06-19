package org.todo.task.pe.view.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.MobileAds
import org.todo.task.pe.view.ui.TasksScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}

        setContent {
            var currentScreen by remember { mutableStateOf("tasks_list") }

            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (currentScreen == "tasks_list") {
                        // Pasamos una función al TasksScreen para que cambie el estado al pulsar +
                        TasksScreen(onNavigateToAdd = { currentScreen = "add_task" })
                    } else {
                        // Pasamos una función para volver atrás
                        AddTaskScreen(onBack = { currentScreen = "tasks_list" })
                    }
                }
            }
        }
    }
}