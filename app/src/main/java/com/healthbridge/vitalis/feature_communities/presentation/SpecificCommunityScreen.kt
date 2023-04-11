package com.healthbridge.vitalis.feature_communities.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.components.CommunityPost
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class SpecificCommunityScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val communityRepository = CommunityRepository()
        val factory = CommunityViewModelFactory(communityRepository)
        val communityViewModel = ViewModelProvider(this, factory).get(CommunityViewModel::class.java)

        communityViewModel.getPost("Sleep")

        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    androidx.compose.material3.Text(
                                        "Communities",
                                        color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                                    )
                                },
                                Modifier.background(color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary),
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
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { /* fab click handler */ }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.chatwidget),
                                    contentDescription = ""
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

