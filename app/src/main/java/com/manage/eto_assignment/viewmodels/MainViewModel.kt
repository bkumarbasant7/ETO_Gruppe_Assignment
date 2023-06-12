package com.manage.eto_assignment.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manage.eto_assignment.data.repository.TaskRepository
import com.manage.eto_assignment.ui.state.TaskUiState
import com.manage.eto_assignment.ui.state.createLocalTask
import com.manage.eto_assignment.ui.state.toTaskUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: TaskRepository) : ViewModel() {

    val allAvailableTask:MutableState<List<TaskUiState>> = mutableStateOf(emptyList())
    fun loadData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    repository.loadTaskFromServer()
                    repository.allTaskObservable.collectLatest {
                        Log.d(TAG, "loadData: ${it.size}")
                        allAvailableTask.value = it.toTaskUiState()
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    }
    fun updateTask(task:TaskUiState){
        viewModelScope.launch {
         withContext(Dispatchers.IO){
             repository.updateTask(task.createLocalTask())
         }
        }
    }

}