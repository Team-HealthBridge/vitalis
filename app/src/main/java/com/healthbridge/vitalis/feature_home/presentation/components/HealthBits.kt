package com.healthbridge.vitalis.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
            .width(170.dp)
            .wrapContentHeight()
    ) {
        Box {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Mental Health Bit Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
                Text(text = "Mental Health", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp))
                Text(text = "Mental health is not a destination... but a process.", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(8.dp))

            }

        }

    }


}