package com.healthbridge.vitalis.feature_auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class ChoiceScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Choice()
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Choice() {

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val scaffoldState = rememberBottomSheetScaffoldState()


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
                        "Welcome",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        "Sign Up/Sign in and make your health a priority. You deserve to feel good and live well.",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(context, SignUpScreen::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                        ) {
                            Text("Sign Up")
                        }
                        Button(
                            onClick = {
                                val intent = Intent(context, SignInScreen::class.java)
                                context.startActivity(intent)
                                      },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                        ) {
                            Text("Sign In")
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
