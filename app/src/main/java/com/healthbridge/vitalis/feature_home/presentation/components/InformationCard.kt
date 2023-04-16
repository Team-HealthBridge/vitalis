package com.healthbridge.vitalis.feature_home.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun InformationCard(image: String, title: String, description: String, url : String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    ElevatedCard(
        onClick = { /* Do something */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(150.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.coming_soon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(150.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = title, style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "See More...",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                        .padding(bottom = 3.dp, end = 8.dp)
                        .clickable {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            })

            }

        }
    }
}