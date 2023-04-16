package com.healthbridge.vitalis.feature_communities.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_communities.data.models.Comment
import com.healthbridge.vitalis.feature_communities.data.models.Post


@Composable
fun CommunityPost(
    post: Post,
    comment: Comment?,
    onClick: () -> Unit
) {

    val liked = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onClick()
                }
            )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "Community Post Profile Image",
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
            if (post.postImage != null && post.postImage.isNotEmpty()){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.postImage)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.coming_soon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            } else{
                post.body?.let {
                    println("Title: $it")
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
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
                    text = post.likes.toString(),
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
                    text = post.comments.size.toString(),
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
                    .padding(horizontal = 30.dp, vertical = 0.dp)
            ) {
//                if (comment.author.profilePicture != null || comment.author.profilePicture != ""){
//                    AsyncImage(
//                        model = ImageRequest.Builder(LocalContext.current)
//                            .data(comment.author.profilePicture)
//                            .crossfade(true)
//                            .build(),
//                        placeholder = painterResource(id = R.drawable.ic_baseline_emoji_emotions_24),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(8.dp)
//                            .align(Alignment.CenterVertically)
//                    )
//                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_emoji_emotions_24),
                        contentDescription = "Community Post Image",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
//                }
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .align(Alignment.CenterVertically)
                ) {
                    if(comment != null){
                        Text(
                            text = comment.body,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    } else {
                        Text(
                            text = "No comments yet",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                }
            }
        }
    }
}
