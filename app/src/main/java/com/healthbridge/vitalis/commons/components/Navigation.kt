package com.healthbridge.vitalis.commons.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R


@Preview(showBackground = true)
@Composable
fun Navigation() {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
    val items = listOf("Home", "Communities", "Records")
    val icons = listOf(
        R.drawable.ic_outline_home_24,
        R.drawable.communities,
        R.drawable.files
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = icons[index]),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                },
                label = { Text(item, color = MaterialTheme.colorScheme.primary) },
                selected = selectedTab == index,
                onClick = { setSelectedTab(index) },
                modifier = Modifier.padding(8.dp)
            )
        }

    }


}