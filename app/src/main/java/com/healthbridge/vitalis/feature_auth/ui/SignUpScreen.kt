package com.healthbridge.vitalis.feature_auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class SignUpScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {

                val authRepository = AuthRepository()
                val viewModel = AuthViewModel(authRepository)
                SignUp(viewModel)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(viewModel: AuthViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val scaffoldState = rememberBottomSheetScaffoldState()
        val purple = Color(0xFFCFBCFF)



        val email = remember { mutableStateOf(TextFieldValue()) }
        val userName = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val confirmPassword = remember { mutableStateOf(TextFieldValue()) }

        val passwordVisible = rememberSaveable { mutableStateOf(false) }

        val context = LocalContext.current



        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 128.dp,
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                }
                Text(
                    "Create Account",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = userName.value,
                    onValueChange = { userName.value = it },
                    label = { Text("User Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    placeholder = { Text("Password") },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible.value)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible.value) "Hide password" else "Show password"

                        IconButton(
                            onClick = {
                            passwordVisible.value = !passwordVisible.value
                        }){
                            Icon(imageVector  = image, description)
                        }
                    }
                )

                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    placeholder = { Text("Confirm Password") },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible.value)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff

                        // Please provide localized description for accessibility services
                        val description = if (passwordVisible.value) "Hide password" else "Show password"

                        IconButton(
                            onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }){
                            Icon(imageVector  = image, description)
                        }
                    }
                )


                Button(
                    onClick = {
                        viewModel.firebaseSignUpWithEmailAndPassword(email= email.value.text, password = password.value.text, password2 = confirmPassword.value.text, name = userName.value.text)
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp, top = 20.dp, bottom = 20.dp),
                ) {
                    Text("Sign Up")
                }

                Text(
                    text = "Or sign up with",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp, top = 20.dp, bottom = 20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purple,
                    ),
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Text(
                            text = "Google",
                            modifier = Modifier.padding(start = 10.dp),
                            color = Color.Black
                        )

                    }
                }

            }) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.background_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }


    }
}