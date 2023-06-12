package com.manage.eto_assignment.data.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.manage.eto_assignment.data.response.TaskResponseItem

@Entity(tableName = "task_table")
data class LocalTask(
    @PrimaryKey
    val taskId: Int,
    val title: String,
    val status: Boolean,
)
fun List<TaskResponseItem>.createLocalTaskItems():List<LocalTask>{
    val data = mutableListOf<LocalTask>()
    for (i in this){
        data.add(i.createLocalTask())
    }
    return data
}

fun TaskResponseItem.createLocalTask(): LocalTask {
    return LocalTask(taskId = id, title, completed)
}

