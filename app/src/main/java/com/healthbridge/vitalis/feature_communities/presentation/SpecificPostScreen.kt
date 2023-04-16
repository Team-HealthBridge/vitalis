package com.healthbridge.vitalis.feature_communities.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_communities.data.models.Comment
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.components.CommunityPost
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class SpecificPostScreen: ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this

        val communityRepository = CommunityRepository()
        val factory = CommunityViewModelFactory(communityRepository)
        val communityViewModel = ViewModelProvider(this, factory)[CommunityViewModel::class.java]

        val currentUser = FirebaseAuth.getInstance().currentUser
        val member = Member(
            id = currentUser?.uid.toString(),
            name = currentUser?.displayName.toString(),
        )

        val communityName = intent.getStringExtra("community")
        val postId = intent.getStringExtra("post")
        println("postID: $postId")



        communityViewModel.isMember(member, communityName.toString())

        communityViewModel.getPost(communityName.toString())
        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme{
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    communityName.toString(),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            Modifier.background(color = MaterialTheme.colorScheme.tertiary),
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                    contentDescription = ""
                                )
                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_baseline_menu_24),
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    },
                    bottomBar = {
                        Navigation()
                    },
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {

                        val allPosts = communityViewModel.postResponse.value
                        val post = allPosts?.find { post -> post.body == postId }

                        val postText = remember { mutableStateOf("") }

                        println("post: $post")


                        if (post != null) {
                            if (post.comments != null || post.comments?.isNotEmpty() == true) {
                                LazyColumn(content = {
                                    item {
                                        CommunityPost(
                                            post = post,
                                            comment = post.comments?.get(0),
                                            onClick = {

                                            }
                                        )
                                    }
                                    items(post.comments?.size ?: 0) { index ->
                                        Column(
                                            modifier = Modifier
                                                .padding(20.dp)
                                                .align(Alignment.End)
                                        ) {
                                            Text(
                                                text = post.comments?.get(index)?.body.toString(),
                                                modifier = Modifier.padding(20.dp).background(
                                                    color = MaterialTheme.colorScheme.primaryContainer
                                                ).align(Alignment.CenterHorizontally)
                                            )

                                        }
                                    }

                                    item{
                                        TextField(
                                            value = postText.value,
                                            onValueChange = {
                                                postText.value = it
                                            },
                                            label = {
                                                Text(
                                                    text = "Add comment",
                                                )
                                            },
                                            placeholder = {
                                                Text(
                                                    text = "Contribute to the discussion",
                                                )
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(20.dp)
                                                .align(Alignment.End)
                                            ,
                                            trailingIcon = {
                                                IconButton(onClick = { /*TODO*/ }) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.ic_baseline_send_24),
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                            },
                                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                                            keyboardActions = KeyboardActions(onSend = {
                                                val comment = Comment()
                                                comment.body = postText.value
                                                comment.author = member
                                                communityViewModel.addComment(
                                                    communityName = communityName.toString(),
                                                    post = post!!,
                                                    comment = comment
                                                )

                                            }),
                                        )

                                    }
                                } )

                            } else {
                                CommunityPost(
                                    post = post,
                                    comment = null,
                                    onClick = {

                                    }
                                )
                                TextField(
                                    value = postText.value,
                                    onValueChange = {
                                        postText.value = it
                                    },
                                    label = {
                                        Text(
                                            text = "Add comment",
                                        )
                                    },
                                    placeholder = {
                                        Text(
                                            text = "Contribute to the discussion",
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                        .align(Alignment.End)
                                    ,
                                    trailingIcon = {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_baseline_send_24),
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                                    keyboardActions = KeyboardActions(onSend = {
                                        val comment = Comment()
                                        comment.body = postText.value
                                        comment.author = member
                                        communityViewModel.addComment(
                                            communityName = communityName.toString(),
                                            post = post!!,
                                            comment = comment
                                        )

                                    }),
                                )
                            }


                        }





                    }

                }

            }
        }
    }
}