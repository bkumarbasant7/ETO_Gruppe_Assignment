package com.manage.eto_assignment.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manage.eto_assignment.ui.state.TaskUiState
import com.manage.eto_assignment.ui.task
import com.manage.eto_assignment.ui.theme.complete_color
import com.manage.eto_assignment.ui.theme.complete_stroke_color
import com.manage.eto_assignment.ui.theme.pending_color
import com.manage.eto_assignment.ui.theme.pending_stroke_color

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(task = task) {}
}

@Composable
fun TaskItem(task: TaskUiState, onUpdateTask: (task: TaskUiState) -> Unit) {
    var menuOpened by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            0.1.dp,
            if (!task.isCompleted) pending_stroke_color else complete_stroke_color
        ),
        backgroundColor = if (!task.isCompleted) pending_color else complete_color
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "${task.id}.", modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            Text(text = task.title, modifier = Modifier.weight(4f))
            Text(
                text = task.getStatus(),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(2f)
            )
            Box(modifier = Modifier) {
                IconButton(onClick = {
                    menuOpened = true
                }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "")
                }
                if (menuOpened) {
                    DropdownMenu(expanded = menuOpened, onDismissRequest = {
                        menuOpened = false
                    }) {
                        DropdownMenuItem(onClick = {
                            onUpdateTask(task.copy(isCompleted = !task.isCompleted))
                            menuOpened = false
                        }) {
                            Text(text = "Update Status")
                        }
                        DropdownMenuItem(onClick = {
                            menuOpened = false
                        }) {
                            Text(text = "Cancel")
                        }

                    }
                }
            }
        }
    }


}