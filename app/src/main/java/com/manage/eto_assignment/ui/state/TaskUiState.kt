package com.manage.eto_assignment.ui.state

import com.manage.eto_assignment.data.model.LocalTask

data class TaskUiState(val id: Int, val title: String, val isCompleted: Boolean) {
    override fun equals(other: Any?): Boolean {
        return if (other is TaskUiState) {
            id == other.id && title == other.title && isCompleted == other.isCompleted
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        return result
    }

    fun getStatus(): String {
        return if (isCompleted) "Completed" else "Pending"
    }
}

fun TaskUiState.createLocalTask(): LocalTask {
    return LocalTask(id, title, isCompleted)
}

fun List<LocalTask>.toTaskUiState(): List<TaskUiState> {
    return this.map { TaskUiState(id = it.taskId, title = it.title, it.status) }
}