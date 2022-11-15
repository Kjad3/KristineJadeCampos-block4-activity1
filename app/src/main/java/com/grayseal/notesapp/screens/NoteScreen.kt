package com.grayseal.notesapp.screens

import android.provider.ContactsContract
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.LeadingIconTab
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grayseal.notesapp.ui.theme.sonoFamily
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun NoteScreen(navController: NavController) {
    NoteContent()
}

@Composable
fun NoteContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), horizontalArrangement = Arrangement.Center) {
                LeadingIconTab(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(modifier = Modifier.size(50.dp), imageVector = Icons.Outlined.Add, contentDescription = "Notes", tint = Color(0xFFdaaac0))
                    },
                    text = {
                        Text("Notes", style = (TextStyle(fontSize = 25.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Bold)))
                    })
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 20.dp, start = 20.dp), horizontalArrangement = Arrangement.Start) {
                Text(getCurrentDate(), style = (TextStyle(fontSize = 18.sp, color = Color.Black)), fontFamily = sonoFamily, fontWeight = FontWeight.Normal)
            }
            NoteArea()
        }
    }
}

@Composable
fun NoteArea(){
    var note by remember {
        mutableStateOf("")
    }
    Note(note = note, onNoteChange = {
        note = it
    })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Note(note: String, onNoteChange: (String) -> Unit){
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(horizontalArrangement = Arrangement.Start){
        TextField(
            modifier = Modifier.padding(start = 4.dp),
            value = note,
            onValueChange = onNoteChange,
            placeholder = {
                Text(text = "Write down something...", fontSize = 18.sp, fontFamily = sonoFamily, fontWeight = FontWeight.Light)
            },
            singleLine = false,
            textStyle = TextStyle(fontSize = 18.sp, color = colors.onBackground, fontFamily = sonoFamily, fontWeight = FontWeight.Normal),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.LightGray,
                focusedBorderColor = Color(0xFFFFFFFF),
                unfocusedBorderColor = Color(0xFFFFFFFF),
                cursorColor = Color(0xFF4c6569),
                placeholderColor = Color.LightGray
            )
        )

    }
}
fun getCurrentDate(): String {
    // Get Current Date time in localized style
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
    return current.format(formatter)
}