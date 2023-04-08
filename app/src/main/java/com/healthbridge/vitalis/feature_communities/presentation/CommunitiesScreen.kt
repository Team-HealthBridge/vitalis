package com.healthbridge.vitalis.feature_communities.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_communities.data.repository.CommunityRepository
import com.healthbridge.vitalis.feature_communities.presentation.components.CommunityPost
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModel
import com.healthbridge.vitalis.feature_communities.presentation.viewmodels.CommunityViewModelFactory
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.ui.theme.VitalisTheme


class CommunitiesScreen() : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

                    ) {
                    Column(
                        modifier = Modifier
                            .paddingFromBaseline(top = 100.dp)
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
                                LazyColumn(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    items(4) { idx ->
                                        val resultText = "Suggestion $idx"
                                        ListItem(
                                            headlineContent = { Text(resultText) },
                                            supportingContent = { Text("Additional info") },
                                            leadingContent = {
                                                Icon(
                                                    Icons.Filled.Star,
                                                    contentDescription = null
                                                )
                                            },
                                            modifier = Modifier.clickable {
                                                text.value = resultText
                                                active.value = false
                                            }
                                        )
                                    }
                                }
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
                        CommunityPost()
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
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

            ) {
            Column(
                modifier = Modifier
                    .paddingFromBaseline(top = 100.dp)
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
                        leadingIcon = { Icon(Icons.Default.Menu, contentDescription = null) },
                        trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(4) { idx ->
                                val resultText = "Suggestion $idx"
                                ListItem(
                                    headlineContent = { Text(resultText) },
                                    supportingContent = { Text("Additional info") },
                                    leadingContent = {
                                        Icon(
                                            Icons.Filled.Star,
                                            contentDescription = null
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        text.value = resultText
                                        active.value = false
                                    }
                                )
                            }
                        }
                        Navigation()
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
                CommunityPost()
                Text(
                    text = "Communities",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                LazyRow(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                ) {
                    items(5) {
                        Box(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            HealthBits(
                                image = "https://images.unsplash.com/photo-1487300001871-12053913095d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2340&q=80",
                                title = "Mental Health",
                                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                            )

                        }
                    }
                }
            }

        }
    }
}