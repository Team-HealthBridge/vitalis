package com.healthbridge.vitalis.feature_communities.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.components.CommunityPost
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.ui.theme.VitalisTheme


class CommunitiesScreen() : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        val communityRepository = CommunityRepository()
        val factory = CommunityViewModelFactory(communityRepository)
        val communityViewModel = ViewModelProvider(this, factory).get(CommunityViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContent {
            val text = remember {
                mutableStateOf("")
            }
            val active = remember {
                mutableStateOf(false)
            }


            VitalisTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Communities",
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

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Box(
                            Modifier
                                .semantics { isContainer = true }
                                .zIndex(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {

                            SearchBar(
                                modifier = Modifier.align(Alignment.TopCenter),
                                query = text.value,
                                onQueryChange = { text.value = it },
                                onSearch = { active.value = false },
                                active = active.value,
                                onActiveChange = {
                                    active.value = it
                                },
                                placeholder = { Text("Search for a topic") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Menu,
                                        contentDescription = null
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = null
                                    )
                                },
                            ) {

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Popular Topics",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        val singlePost = communityViewModel.postResponse.value
                        if(singlePost.isNotEmpty()){
                            val comment = singlePost[0].comments
                           CommunityPost(post = singlePost[0], comment = comment[0])
                        }

                        Text(
                            text = "Communities",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                        LazyRow(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 20.dp),

                            content = {
                                val size = communityViewModel.communitiesResponse.value.size
                                items(size) {
                                    communityViewModel.communitiesResponse.value[it].let { community ->
                                        community.profilePicture?.let { it1 ->
                                            Box(modifier = Modifier.padding(10.dp)){
                                                HealthBits(
                                                    image = it1,
                                                    title = community.name,
                                                    description = community.bio
                                                )
                                            }
                                        }
                                    }

                                }
                            }
                        )

                    }

                }
            }
        }
    }
}