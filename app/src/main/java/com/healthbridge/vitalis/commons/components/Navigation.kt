package com.healthbridge.vitalis.commons.components

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_communities.presentation.CommunitiesScreen
import com.healthbridge.vitalis.feature_home.presentation.MainActivity
import com.healthbridge.vitalis.feature_records.presentation.RecordsScreen



@Composable
fun Navigation(
) {
    val context = LocalContext.current
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_home_24),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = selectedTab == 0,
            onClick = {
                setSelectedTab(0)
                startActivity(context, Intent(context, MainActivity::class.java), null)
            },
            modifier = Modifier.padding(8.dp)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.communities),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = selectedTab == 1 ,
            onClick = {
                setSelectedTab(1)
                startActivity(context, Intent(context, CommunitiesScreen::class.java), null)
            },
            modifier = Modifier.padding(8.dp)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.files),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = selectedTab == 2,
            onClick = {
                setSelectedTab(2)
                startActivity(context, Intent(context, RecordsScreen::class.java), null)
            },
            modifier = Modifier.padding(8.dp)
        )

    }


}
