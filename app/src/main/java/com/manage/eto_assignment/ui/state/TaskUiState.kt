package com.manage.eto_assignment.ui.state

import com.manage.eto_assignment.data.model.LocalTask
import okhttp3.internal.concurrent.Task

data class TaskUiState(val id: Int, val title: String, val isCompleted: Boolean) {
    fun getStatus(): String {
        return if (isCompleted) "Completed" else "Pending"
    }
}
fun TaskUiState.createLocalTask():LocalTask{
    return LocalTask(id,title,isCompleted)
}

fun List<LocalTask>.toTaskUiState(): List<TaskUiState> {
    return this.map { TaskUiState(id = it.taskId, title = it.title, it.status) }
}