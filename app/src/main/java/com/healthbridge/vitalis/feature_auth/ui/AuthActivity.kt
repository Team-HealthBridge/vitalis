package com.healthbridge.vitalis.feature_auth.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.healthbridge.vitalis.feature_home.presentation.MainActivity

class AuthActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            println("AuthActivity on Create")
            AuthState()


        }
    }


    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    private fun AuthState() {
        val authRepository = AuthRepository()
        val viewModel = AuthViewModel(authRepository)
        val context = this
        val isUserSignedOut = viewModel.getAuthState().value
        if (isUserSignedOut) {
            println("User is signed out")
            val intent = Intent(context, ChoiceScreen::class.java)
            context.startActivity(intent)
        } else {
            println("User is signed in")
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}

