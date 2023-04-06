package com.healthbridge.vitalis.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationCard(modifier: Modifier) {
    ElevatedCard(
        onClick = { /* Do something */ },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.healthimage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(100.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Health Center", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "All your health information in one place!",
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "See More...",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.End)
                )

            }

        }
    }
}