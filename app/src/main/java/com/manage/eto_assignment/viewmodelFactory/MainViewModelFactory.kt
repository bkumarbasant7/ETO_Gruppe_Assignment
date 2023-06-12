package com.manage.eto_assignment.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manage.eto_assignment.data.repository.TaskRepository
import com.manage.eto_assignment.viewmodels.MainViewModel

class MainViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw Exception("Unknown ViewModel Class")
    }
}