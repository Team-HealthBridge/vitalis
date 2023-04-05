package com.healthbridge.vitalis.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthBits() {
    
    ElevatedCard(
        onClick = { /* Do something */ },
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Box(
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Mental Health Bit Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text(text = "Mental Health", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Mental health is not a destination... but a process.", style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "See More",
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }

        }

    }


}