package com.healthbridge.vitalis.feature_bot.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R


@Composable
fun MessageInput(onSend: (String) -> Unit) {

    val value = rememberSaveable { mutableStateOf("") }
    LocalContext.current.applicationContext

    val focusManager = LocalFocusManager.current


    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        label = { Text("Start typing...") },
        placeholder = { Text("") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = {
            focusManager.clearFocus()
            onSend(value.value)
            value.value = ""

        }),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_emoji_emotions_24),
                contentDescription = "Mic"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                focusManager.clearFocus()
                onSend(value.value)
                value.value = ""
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_send_24),
                    contentDescription = "Send"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.small, clip = true)

    )
}






