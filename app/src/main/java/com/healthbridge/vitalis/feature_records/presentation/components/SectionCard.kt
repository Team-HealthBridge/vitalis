package com.healthbridge.vitalis.feature_records.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionCard(image: Int, title: String, description: String, onClick: () -> Unit) {
    ElevatedCard(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(horizontal =  16.dp)
                )

            }

            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24), contentDescription = null )

            }


        }

    }
}