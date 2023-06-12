package com.manage.eto_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.manage.eto_assignment.data.repository.TaskRepository
import com.manage.eto_assignment.ui.component.TaskItem
import com.manage.eto_assignment.ui.component.TodoListHeader
import com.manage.eto_assignment.ui.listOftask
import com.manage.eto_assignment.ui.state.TaskUiState
import com.manage.eto_assignment.ui.theme.ETO_AssignmentTheme
import com.manage.eto_assignment.viewmodelFactory.MainViewModelFactory
import com.manage.eto_assignment.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(TaskRepository(applicationContext))
        )[MainViewModel::class.java]
        viewModel.loadData()
        setContent {
            ETO_AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    MainScreen(viewModel.allAvailableTask.value) {
                        viewModel.updateTask(it)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(name: List<TaskUiState>, onUpdateTask: (task: TaskUiState) -> Unit) {

    Column {
        TopAppBar {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Menu, contentDescription = "")
            }
            Text(text = stringResource(R.string.task))
        }
        Column(Modifier.padding(8.dp)) {
            TodoListHeader()
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(name) { _, item ->
                    TaskItem(task = item, onUpdateTask)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ETO_AssignmentTheme {
        MainScreen(listOftask) {}
    }
}