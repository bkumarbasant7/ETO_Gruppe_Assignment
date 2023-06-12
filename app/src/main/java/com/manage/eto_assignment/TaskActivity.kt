package com.manage.eto_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.manage.eto_assignment.adapter.TaskAdapter
import com.manage.eto_assignment.data.repository.TaskRepository
import com.manage.eto_assignment.databinding.ActivityTaskBinding
import com.manage.eto_assignment.viewmodelFactory.MainViewModelFactory
import com.manage.eto_assignment.viewmodels.MainViewModel

class TaskActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityTaskBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(TaskRepository(applicationContext))
        )[MainViewModel::class.java]
        viewModel.loadData()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
    }

    override fun onStart() {
        super.onStart()
        binding.taskRecycler.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(this)
        adapter.setListener { it, onComplete ->
            viewModel.updateTask(it.copy(isCompleted = !it.isCompleted))
            onComplete()
        }
        binding.taskRecycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.allAvailableTaskObservable.observe(this) {
            adapter.updateItems(it)
        }


    }
}