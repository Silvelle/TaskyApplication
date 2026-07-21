package com.example.taskyapplication.presentation.notes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoteCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "How to make professinal frame",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier= Modifier.height(8.dp))
            Text(
                text = "Here's ordinary text Lorem the luck was gone impoisd what do you medn speed live die young",
                maxLines = 4
            )
            Spacer(modifier= Modifier.height(16.dp))
            Row() {
                Text(
                    text = "Tag1",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
fun ListOfNotes(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.padding(12.dp).fillMaxSize()) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(3) {
                NoteCard()
            }
        }
        FloatingActionButton(onClick = {/*TODO*/},
            modifier = modifier.align ( Alignment.BottomEnd )
                .offset(x = (-12).dp, y=(-20).dp).size(48.dp),
            shape = CircleShape,
            containerColor = Color.Black,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
        }
    }

@Composable
fun DisplayScreen(modifier: Modifier = Modifier) {
    ListOfNotes()
}


@Preview(showBackground = true, heightDp = 800, widthDp = 360)
@Composable
fun DemoDisplayScreen () {
    ListOfNotes()
}
