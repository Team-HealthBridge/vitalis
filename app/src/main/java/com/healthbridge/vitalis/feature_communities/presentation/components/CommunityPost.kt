package com.healthbridge.vitalis.feature_communities.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommunityPost() {

    val liked = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "Community Post Image",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                )
                Text(
                    text = "Sleep",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Follow",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )

            }
            Image(
                painter = painterResource(id = R.drawable.post),
                contentDescription = "Community Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            // Interactions Row (Like, Comment, Share)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                IconToggleButton(
                    checked = liked.value,
                    onCheckedChange = { liked.value = it }) {

                    if (liked.value) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                            contentDescription = "Community Post Image",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                            contentDescription = "Community Post Image",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                Text(
                    text = "5.3K",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_mode_comment_24),
                    contentDescription = "Community Post Image",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "1.7K",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_bookmark_border_24),
                    contentDescription = "Community Post Image",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            // Comments
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.member_profile_image),
                    contentDescription = "Community Post Image",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        ).align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "I am a permanently exhausted pigeon",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally)
                    )

                }
            }
        }
    }
}