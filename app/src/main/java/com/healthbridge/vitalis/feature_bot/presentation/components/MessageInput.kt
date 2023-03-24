package com.healthbridge.vitalis.feature_bot.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R



@Composable
fun MessageInput(modifier: Modifier = Modifier) {
    var value = rememberSaveable { mutableStateOf("") }

    TextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text("Message") },
        placeholder = { Text("Type a message") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_emoji_emotions_24),
                contentDescription = "Mic"
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_send_24),
                contentDescription = "Send"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.small, clip = true)

    )

}






