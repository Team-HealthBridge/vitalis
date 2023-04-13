package com.healthbridge.vitalis.feature_auth.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.ui.theme.VitalisTheme


class OnboardScreen: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           VitalisTheme() {
                Onboarding()
           }
        }
    }
}

@Preview
@Composable
fun Onboarding() {

    var pageNumber by remember { mutableStateOf(1) }
    val context = LocalContext.current

    val titleResource = when(pageNumber) {
        1 -> R.string.title
        2 -> R.string.title2
        3 -> R.string.title3
        else -> R.string.title4
    }

    val descriptionResource = when(pageNumber) {
        1 -> R.string.welcome_message
        2 -> R.string.welcome_message2
        3 -> R.string.welcome_message_3
        else -> R.string.welcome_message_4
    }

    val imageResource = when(pageNumber) {
        1 -> R.drawable.onboarding_1
        2 -> R.drawable.onboarding_2
        3 -> R.drawable.onboarding_3
        else -> R.drawable.onboarding_4
    }

    val progress = when(pageNumber) {
        1 -> 0.25f
        2 -> 0.5f
        3 -> 0.75f
        else -> 1f
    }
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = Color.Black,
                lineHeight = 20.sp,
                style = TextStyle(
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp,
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp, top = 16.dp)
                    .clickable {
                        val intent = Intent(context, ChoiceScreen::class.java)
                        context.startActivity(intent)
                    }
            )
            Spacer(
                modifier = Modifier
                    .height(height = 56.dp)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(titleResource),
                color = Color(0xff6750a4),
                lineHeight = 28.sp,
                style = TextStyle(
                    fontSize = 24.sp,
                    letterSpacing = 0.25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(
                modifier = Modifier
                    .height(height = 23.dp)
            )
            Text(
                text = stringResource(descriptionResource),
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                style = TextStyle(
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(width = 256.dp)
                    .height(height = 103.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(height = 42.dp)
            )
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(width = 267.dp)
                    .height(height = 246.dp)
                    .padding(start = 16.dp, end = 16.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(height = 28.dp)
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(width = 150.dp)
                    .height(height = 4.dp)
                    .progressSemantics(progress)
                    .clip(RoundedCornerShape(100.dp))
            )
            Spacer(
                modifier = Modifier
                    .height(height = 100.dp)
            )
            if (pageNumber == 4) {
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(width = 150.dp)
                        .height(height = 56.dp),
                    onClick = {
                        val intent = Intent(context, ChoiceScreen::class.java)
                        context.startActivity(intent)
                    }
                ) {

                    Text(
                        text = stringResource(R.string.get_started),
                        color = Color.White,
                        lineHeight = 20.sp,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 0.25.sp,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            else {
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(width = 56.dp)
                        .height(height = 56.dp),
                    onClick = {
                        pageNumber++
                    },
                    containerColor = Color(0xfffffbfe),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(all = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon),
                            contentDescription = "icon",
                            tint = Color(0xff6750a4)
                        )
                    }
                }
            }
        }
    }
}