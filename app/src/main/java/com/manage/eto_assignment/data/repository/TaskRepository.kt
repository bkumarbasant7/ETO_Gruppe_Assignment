package com.manage.eto_assignment.data.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.manage.eto_assignment.data.LocalDb
import com.manage.eto_assignment.data.dao.RemoteDao.Companion.client
import com.manage.eto_assignment.data.model.LocalTask
import com.manage.eto_assignment.data.model.createLocalTaskItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.log

class TaskRepository(private val context: Context) {
    private val taskDao = LocalDb.getInstance(context)?.taskDao()
    private val IO_Scope = CoroutineScope(Dispatchers.IO)
    private val allAvailableTask: MutableStateFlow<List<LocalTask>> = MutableStateFlow(emptyList())
    val allTaskObservable: StateFlow<List<LocalTask>> = allAvailableTask


    fun loadTaskFromServer() {
        IO_Scope.launch {
            try {
                val response = client.loadTask()
                if (response.isSuccessful) {
                    response.body()?.let {
                        taskDao?.upsertAllTask(it.createLocalTaskItems())
                        getAllTask()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getAllTask() {
        try {
            taskDao?.getAllTask()?.collectLatest {
                allAvailableTask.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateTask(task: LocalTask) {
        taskDao?.updateTask(task)
    }
}