package org.lyrebird.spaceexplorer.ui.screen.launchDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.lyrebird.spaceexplorer.SurfaceLight
import org.lyrebird.spaceexplorer.TopAppBarColor
import org.lyrebird.spaceexplorer.TopAppBarContentColor
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchDetailState
import org.lyrebird.spaceexplorer.ui.component.launchDetail.DetailBottomBar
import org.lyrebird.spaceexplorer.ui.component.common.ErrorView
import org.lyrebird.spaceexplorer.ui.component.launchDetail.HeroSection
import org.lyrebird.spaceexplorer.ui.component.launchDetail.LaunchDetailSkeleton
import org.lyrebird.spaceexplorer.ui.component.launchDetail.LaunchHeaderCard
import org.lyrebird.spaceexplorer.ui.component.launchDetail.LaunchOverview
import org.lyrebird.spaceexplorer.ui.component.launchDetail.RocketDetailsCard
import org.lyrebird.spaceexplorer.ui.viewmodel.LaunchDetail.LaunchDetailViewModel
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.launch_detail
import spaceexplorer.composeapp.generated.resources.mission_overview
import spaceexplorer.composeapp.generated.resources.no_details_available

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailScreen(
    navController: NavController,
    launchID: String,
    rocketID: String,
    viewModel: LaunchDetailViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchData(launchID, rocketID)
    }

    Scaffold(
        containerColor = SurfaceLight,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.launch_detail)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBackIos, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TopAppBarColor,
                    titleContentColor = TopAppBarContentColor,
                    navigationIconContentColor = TopAppBarContentColor
                ),
            )
        },
        bottomBar = {
            if (state is LaunchDetailState.Success) {
                val model = (state as LaunchDetailState.Success).data
                DetailBottomBar(model)
            }
        }
    ) { padding ->
        when (val uiState = state) {
            is LaunchDetailState.Loading -> {
                LaunchDetailSkeleton()
            }
            is LaunchDetailState.Error -> {
                ErrorView(
                    onRetry = { viewModel.fetchData(launchID, rocketID) }
                )
            }
            is LaunchDetailState.Success -> {
                val model = uiState.data

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    item { HeroSection(model) }

                    item { LaunchHeaderCard(model) }

                    item {
                        LaunchOverview(
                            title = stringResource(Res.string.mission_overview),
                            launchDetails = model.launchDetails ?: stringResource(Res.string.no_details_available)
                        )
                    }

                    item {
                        RocketDetailsCard(model)
                    }
                }
            }
        }
    }
}