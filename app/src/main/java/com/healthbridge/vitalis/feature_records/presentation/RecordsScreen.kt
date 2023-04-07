package com.healthbridge.vitalis.feature_records.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.ui.theme.VitalisTheme


class RecordsScreen(): ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent{

            VitalisTheme {
                Scaffold(
                    topBar = {

                    },
                    bottomBar = {
                        Navigation()
                    },
                ) {
                    Text(text = "Records")
                }

            }
        }
    }

}