package org.lyrebird.spaceexplorer.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.lyrebird.spaceexplorer.Black
import org.lyrebird.spaceexplorer.SurfaceLight
import org.lyrebird.spaceexplorer.TopAppBarColor
import org.lyrebird.spaceexplorer.TopAppBarContentColor
import org.lyrebird.spaceexplorer.ui.state.launch.LaunchListState
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchDetail
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchUIModel
import org.lyrebird.spaceexplorer.ui.component.common.ErrorView
import org.lyrebird.spaceexplorer.ui.component.home.LaunchCard
import org.lyrebird.spaceexplorer.ui.component.home.LaunchListSkeleton
import org.lyrebird.spaceexplorer.ui.viewmodel.Launch.LaunchViewModel
import spaceexplorer.composeapp.generated.resources.Res
import spaceexplorer.composeapp.generated.resources.launches_list

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LaunchViewModel = koinViewModel<LaunchViewModel>()
){
    val state = viewModel.state.collectAsState()

    Scaffold(
        containerColor = SurfaceLight,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(Res.string.launches_list),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = Black,
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TopAppBarColor,
                    titleContentColor = TopAppBarContentColor
                ),
            )
        },
    ) { padding ->
        Box(
            modifier =
                Modifier
                    .padding(padding)
                    .background(color = SurfaceLight)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    when (val uiState = state.value) {
                        is LaunchListState.Loading -> {
                            LaunchListSkeleton()
                        }
                        is LaunchListState.Success -> {
                            LaunchList(
                                launches = uiState.launches,
                                navController = navController)
                        }
                        is LaunchListState.Error -> {
                            ErrorView(
                                onRetry = { viewModel.fetchLaunches() }
                            )
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun LaunchList(launches: List<LaunchUIModel>, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(launches.count()) { i ->
            val launch = launches[i]
            LaunchCard(
                launch  = launch,
                onClick = {
                    navController.navigate(
                        LaunchDetail(
                            launchID = launch.id,
                            rocketID = launch.rocketId
                        )
                    )
                }
            )
        }
    }
}
