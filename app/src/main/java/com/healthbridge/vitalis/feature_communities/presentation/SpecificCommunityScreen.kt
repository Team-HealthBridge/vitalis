package com.healthbridge.vitalis.feature_communities.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_communities.data.models.Member
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.components.CommunityPost
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class SpecificCommunityScreen : ComponentActivity() {
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

        communityViewModel.isMember(member, communityName.toString())

        communityViewModel.getPost(communityName.toString())

        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = communityName.toString(),
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                },
                                Modifier.background(color = MaterialTheme.colorScheme.tertiary),
                                navigationIcon = {
                                    IconButton(onClick = {
                                        onBackPressed()
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                            contentDescription = ""
                                        )

                                    }
                                },
                                actions = {
                                    val isFollowing = communityViewModel.isMember
                                    if (!isFollowing.value){
                                        Button(
                                            onClick = {
                                                communityViewModel.followCommunity("Sleep", member)
                                            },
                                            shape = RoundedCornerShape(20),
                                        ) {
                                            Text(
                                                text = "Follow",
                                            )
                                        }
                                    } else{
                                        Button(
                                            onClick = {
                                                val intent = Intent(context, NewPostScreen::class.java)
                                                intent.putExtra("community", "Sleep")
                                                context.startActivity(intent)
                                            },
                                            shape = RoundedCornerShape(20),
                                        ){
                                            Text(
                                                text = "POST",
                                            )
                                        }
                                    }
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    startActivity(Intent(this, ChatScreen::class.java))
                                },
                                shape = CircleShape, // this makes the button round

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chatwidget),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(50.dp)
                                )
                            }
                        },
                        bottomBar = {
                            Navigation()
                        },

                        ) { innerPadding ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            content =  {
                                val singlePost = communityViewModel.postResponse.value
                                if(singlePost.isNotEmpty()){
                                    println("Single Post: $singlePost")
                                    singlePost.forEach {
                                        println("Single Post: $it")
                                        if(it.comments.isNotEmpty() && it.comments != null){
                                            item{
                                            CommunityPost(post = it, comment = it.comments[0])
                                            }
                                        } else {
                                            item{
                                                CommunityPost(post = it, comment = null)
                                            }
                                        }


                                    }
                                }

                        } )
                    }
                }
            }
        }
    }
}

