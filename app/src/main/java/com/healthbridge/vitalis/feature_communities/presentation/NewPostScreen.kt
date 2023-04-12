package com.healthbridge.vitalis.feature_communities.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.models.Post
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class NewPostScreen: ComponentActivity(){

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val communityRepository = CommunityRepository()
        val factory = CommunityViewModelFactory(communityRepository)
        val communityViewModel = ViewModelProvider(this, factory)[CommunityViewModel::class.java]

        val communityName = intent.getStringExtra("community")


        val postText: MutableState<String> = mutableStateOf("")

        val currentUser = FirebaseAuth.getInstance().currentUser
        val member = Member(
            id = currentUser?.uid.toString(),
            name = currentUser?.displayName.toString(),
        )

        setContent {
            VitalisTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        topBar = {
                                 CenterAlignedTopAppBar(
                                        title = {
                                            Text(
                                                text = "New Post",
                                                color = MaterialTheme.colorScheme.primary
                                            )

                                        },
                                        navigationIcon = {
                                            IconButton(onClick = {
                                                onBackPressed()
                                            }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        },
                                        actions = {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        },
                                    )
                        },
                        bottomBar = {

                        },
                        ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {

                            Text(
                                text = "Create Post for ${communityName.toString()}",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(20.dp)
                            )
                            
                            TextField(
                                value = postText.value,
                                onValueChange = {
                                    postText.value = it
                                },
                                label = {
                                    Text(
                                        text = "Post",
                                    )
                                },
                                placeholder = {
                                    Text(
                                        text = "Enter your post or question here",
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
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
                                    val post = Post()
                                    post.author = member
                                    post.body = postText.value
                                    communityViewModel.addPost(communityName.toString(), post)

                                }),
                            )
                            Text(
                                text = "* Required",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(start = 20.dp, top = 0.dp)
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        Text(text = "Image", style = MaterialTheme.typography.headlineSmall)
                                        Text(text = "Optional", style = MaterialTheme.typography.bodySmall)

                                    }
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                    ) {
                                        Button(
                                            onClick = { /*TODO*/ }
                                        ) {
                                           Text(text = "Upload")
                                        }
                                    }

                                }

                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                                horizontalArrangement = Arrangement.Center,
                            ){
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Post")
                                }
                                Spacer(modifier = Modifier.width(20.dp))
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Discard")
                                }

                            }



                        }


                    }

                }

            }
        }
    }

}