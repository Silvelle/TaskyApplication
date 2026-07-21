package com.example.taskyapplication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.taskyapplication.data.local.database.NoteDatabase
import com.example.taskyapplication.data.local.entity.NoteEntity
import kotlinx.coroutines.launch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.taskyapplication.presentation.notes.DisplayScreen
import com.example.taskyapplication.presentation.ui.theme.TaskyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DatabaseTestScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun DatabaseTestScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()

    val database = remember {
        NoteDatabase.getDataBase(context)
    }

    val notes by database.noteDao()
        .getAllNotes()
        .collectAsState(initial = emptyList())

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {
                scope.launch {
                    val currentTime = System.currentTimeMillis()

                    database.noteDao().insert(
                        NoteEntity(
                            title = "Test note",
                            content = "Inserted at $currentTime",
                            createdAt = currentTime,
                            updatedAt = currentTime,
                            isPinned = false,
                            isArchieved = false,
                            color = null,
                            tags = emptyList()
                        )
                    )
                }
            }
        ) {
            Text("Insert test note")
        }

        Text("Notes in database: ${notes.size}")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = notes,
                key = { note -> note.id }
            ) { note ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(note.content)
                        Text("Database ID: ${note.id}")
                    }
                }
            }
        }
    }
}