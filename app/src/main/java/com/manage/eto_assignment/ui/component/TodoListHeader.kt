package com.manage.eto_assignment.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TodoListHeaderPreview() {
    TodoListHeader()
}

@Composable
fun TodoListHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "No.", modifier = Modifier.weight(1f))
        Text(text = "Task", modifier = Modifier.weight(4f))
        Text(text = "Status", textAlign = TextAlign.Center, modifier = Modifier.weight(2f))

            Icon(Icons.Default.List, contentDescription = "", tint = Color.Transparent)

    }
}