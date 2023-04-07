package com.healthbridge.vitalis.commons.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.healthbridge.vitalis.commons.navigation.BottomNavItem


@Composable
fun Navigation(
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Communities,
        BottomNavItem.Records,
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute  = remember(navBackStackEntry) {
            navBackStackEntry?.destination?.route
        }
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        println("Current route is $currentRoute")
                        println("Item route is ${item.route}")
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }

                    }
                },
                modifier = Modifier.padding(8.dp)
            )
        }

    }


}
