package com.healthbridge.vitalis.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.healthbridge.vitalis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthBits(image: String, title: String, description: String) {
    
    ElevatedCard(
        onClick = { /* Do something */ },
        modifier = Modifier
            .width(170.dp)
            .wrapContentHeight()
    ) {
        Box {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.coming_soon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text(text = title, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp))
                Text(text = description, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp))

            }

        }

    }


}