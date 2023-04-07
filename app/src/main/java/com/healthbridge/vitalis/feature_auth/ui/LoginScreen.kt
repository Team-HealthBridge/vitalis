package com.healthbridge.vitalis.feature_auth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class LoginScreen : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var listener: FirebaseAuth.AuthStateListener

    lateinit var providers: List<AuthUI.IdpConfig>

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(listener)
    }

    override fun onStop() {
        if (listener != null) {
            auth.removeAuthStateListener(listener)
        }
        super.onStop()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            VitalisTheme {

                // A surface container using the 'background' color from the theme

            }
        }

        init()
    }

    private fun init() {
        providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        auth = FirebaseAuth.getInstance()

        listener = FirebaseAuth.AuthStateListener { p0 ->
            val user = p0.currentUser
            if (user != null) {


            } else {
                // User is signed out
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.ic_launcher)
                        .setTheme(R.style.Auth)
                        .build(),
                    1234
                )
            }
        }

    }

}


