package com.healthbridge.vitalis.commons.navigation

import com.healthbridge.vitalis.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    object Home : BottomNavItem("home", R.drawable.ic_outline_home_24, "Home")
    object Communities : BottomNavItem("communities", R.drawable.communities, "Communities")
    object Records : BottomNavItem("records", R.drawable.files, "Records")
}
