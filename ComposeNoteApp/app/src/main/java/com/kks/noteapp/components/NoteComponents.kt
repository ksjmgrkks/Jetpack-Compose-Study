package com.kks.noteapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.kks.noteapp.model.Note
import com.kks.noteapp.ui.theme.SkyBlue
import com.kks.noteapp.ui.theme.ThemeSkyBlue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground,
            cursorColor = ThemeSkyBlue,
            focusedLabelColor = MaterialTheme.colors.primary,
            placeholderColor = Color.Gray
        ),
        maxLines = maxLine,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )

}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
){
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }

}

@Composable
fun EditNoteDialog(
    note: Note,
    onDismiss: () -> Unit,
    onSave: (Note) -> Unit,
) {
    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "노트 수정하기") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "제목") },
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "내용") },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(Note(
                        id = note.id,
                        title = title,
                        description = description,
                        entryDate = note.entryDate,
                    ))
                    onDismiss()
                }
            ) {
                Text(text = "수정하기")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "취소하기")
            }
        },
    )
}
