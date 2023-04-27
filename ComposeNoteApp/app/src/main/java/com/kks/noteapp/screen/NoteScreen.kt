package com.kks.noteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kks.noteapp.R
import com.kks.noteapp.components.NoteButton
import com.kks.noteapp.components.NoteInputText
import com.kks.noteapp.data.NoteDataSource
import com.kks.noteapp.model.Note
import com.kks.noteapp.ui.theme.SkyBlue
import com.kks.noteapp.util.formatDate

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name),
                              color = Color.Black,)
        },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            },
        backgroundColor = SkyBlue
        )

        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = title,
                label = "제목",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                        title = it
                })

            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = description,
                label = "내용",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        })
                        description = it
                })

            NoteButton(
                text = "기록하기",
                backgroundColor = SkyBlue,
                contentColor = Color.Black,
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        title = ""
                        description = ""
                    }
                })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {})
            }

        }
        
    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = SkyBlue,
        elevation = 6.dp) {
        Column(modifier
            .clickable { onNoteClicked(note) }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title,
                style = MaterialTheme.typography.subtitle2,
                color = Color.Black)
            Text(text = note.description,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black)
            Text(
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption,
                color = Color.Black,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}