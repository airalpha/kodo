package ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.RequestState
import domain.TodoTask

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(text = "Home") }) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    shape = RoundedCornerShape(size = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon"
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
            ) {
            }
        }
    }
}

@Composable
fun DisplayTasks(
    modifier: Modifier = Modifier,
    tasks: RequestState<List<TodoTask>>,
    showActive: Boolean = true,
    onSelect: ((TodoTask) -> Unit)? = null,
    onFavorite: ((TodoTask, Boolean) -> Unit)? = null,
    onDelete: ((TodoTask) -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(false) }
    var taskToDelete: TodoTask? by remember { mutableStateOf(null) }

    if (showDialog) {
        AlertDialog(
            title = {
                Text("Delete", fontSize = MaterialTheme.typography.titleLarge.fontSize)
            },
            text = {
                Text("Are you sure you want to remove '${taskToDelete!!.title}'")
            },
            confirmButton = {
                Button(onClick = {
                    onDelete?.invoke(taskToDelete!!)
                    showDialog = false
                    taskToDelete = null
                }) {
                    Text("Yess")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    taskToDelete = null
                    showDialog = false
                }) {
                    Text("Cancel")
                }
            },
            onDismissRequest = {
                taskToDelete = null
                showDialog = false
            }
        )
    }
}