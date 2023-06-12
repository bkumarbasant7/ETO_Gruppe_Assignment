package com.manage.eto_assignment.data.response

class TaskResponse : ArrayList<TaskResponseItem>()

data class TaskResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)