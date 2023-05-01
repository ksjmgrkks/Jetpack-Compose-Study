package com.kks.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kks.noteapp.R
import com.kks.noteapp.components.NoteButton
import com.kks.noteapp.components.NoteInputText
import com.kks.noteapp.model.Note
import com.kks.noteapp.ui.theme.SkyBlue
import com.kks.noteapp.ui.theme.ThemeSkyBlue
import com.kks.noteapp.util.formatDate

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onRemoveAllNote: () -> Unit
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name),
                              color = Color.Black,)
        },
            actions = {
                var showDialog by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { showDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "노트 전체 삭제 아이콘",
                        tint = ThemeSkyBlue,
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("전체 삭제") },
                        text = { Text("전체 항목을 삭제하시겠습니까?") },
                        confirmButton = {
                            Button(onClick = {
                                showDialog = false
                                onRemoveAllNote()
                            }) {
                                Text("예")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { showDialog = false }) {
                                Text("아니요")
                            }
                        }
                    )
                }
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
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "노트가 기록되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note)
                })
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
        var showDialog by remember { mutableStateOf(false) }
        Column(modifier
            .clickable { showDialog = true }
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
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("삭제") },
                text = { Text("선택한 항목을 삭제하시겠습니까?") },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        onNoteClicked(note)
                    }) {
                        Text("예")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("아니요")
                    }
                }
            )
        }
    }
}