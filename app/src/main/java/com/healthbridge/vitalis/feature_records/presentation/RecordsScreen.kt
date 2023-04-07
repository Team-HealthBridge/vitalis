package com.healthbridge.vitalis.feature_records.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.commons.navigation.BottomNavGraph
import com.healthbridge.vitalis.ui.theme.VitalisTheme

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordsScreen() {
    val navController = rememberNavController()

    VitalisTheme {
        Scaffold(
            topBar = {

            },
            bottomBar = {
                        Navigation(navController = navController)
            },
        ) {

            BottomNavGraph(navController = navController)
            Text(text = "Records")
        }

    }
}