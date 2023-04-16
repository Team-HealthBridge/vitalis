package com.healthbridge.vitalis.feature_home.presentation

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_auth.ui.AuthActivity
import com.healthbridge.vitalis.feature_auth.ui.AuthRepository
import com.healthbridge.vitalis.feature_auth.ui.AuthViewModel
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_home.data.repository.HealthBitsRepository
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.feature_home.presentation.viewmodels.HealthBitsViewModel
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class MoreHealthBitsScreen : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this

        val authRepository = AuthRepository()
        val viewModel = AuthViewModel(authRepository)

        val healthBitsRepository = HealthBitsRepository()
        val healthBitsViewModel = HealthBitsViewModel(healthBitsRepository)
        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Health Bits",
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
                                val expanded = remember { mutableStateOf(false) }
                                IconButton(onClick = { expanded.value = true }) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_baseline_more_vert_24),
                                        contentDescription = "Menu"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false },
                                ) {
                                    DropdownMenuItem(
                                        onClick = {
                                            viewModel.firebaseSignOut()
                                            val intent = Intent(context, AuthActivity::class.java)
                                            startActivity(intent)

                                        },
                                        text = { Text(text = "Log Out") },
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_baseline_arrow_outward_24),
                                                contentDescription = null
                                            )
                                        }
                                    )

                                }
                            }
                        )

                    },
                    bottomBar = {
                        Navigation()
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
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(130.dp)
                        ) {
                            val cellConfiguration =
                                if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                    StaggeredGridCells.Adaptive(minSize = 30.dp)
                                } else {
                                    StaggeredGridCells.Fixed(2)
                                }

                            LazyHorizontalStaggeredGrid(
                                rows = cellConfiguration,
                                contentPadding = PaddingValues(16.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalItemSpacing = 16.dp,
                                content = {
                                    val size = healthBitsViewModel.categories.value.size
                                    items(size) {
                                        healthBitsViewModel.categories.value[it].let { category ->
                                            val selected: MutableState<Boolean> =
                                                remember { mutableStateOf(false) }
                                            val color = Color(0xFFCFBCFF)
                                            FilterChip(
                                                selected = selected.value,
                                                onClick = {
                                                    selected.value = !selected.value
                                                    if (selected.value) {
                                                        healthBitsViewModel.getHealthBitsByCategory(
                                                            category
                                                        )
                                                    }
                                                },
                                                label = { Text(category) },
                                                modifier = Modifier.height(30.dp),
                                                colors = FilterChipDefaults.filterChipColors(
                                                    selectedContainerColor = color,
                                                ),
                                                leadingIcon = if (selected.value) {
                                                    {
                                                        Icon(
                                                            imageVector = Icons.Filled.Done,
                                                            contentDescription = null,
                                                            modifier = Modifier.size(
                                                                FilterChipDefaults.IconSize
                                                            )
                                                        )
                                                    }
                                                } else {
                                                    null
                                                }
                                            )
                                        }
                                    }
                                }
                            )
                        }
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp), // this adds padding around the whole grid
                            modifier = Modifier.fillMaxHeight(0.9f)
                        ) {
                            healthBitsViewModel.healthBitsByCategory.value.forEach {
                                item {
                                    Box(modifier = Modifier.padding(10.dp)) { // this adds padding around each item
                                        HealthBits(
                                            image = it.pictureUrl,
                                            title = it.category,
                                            description = it.description
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

