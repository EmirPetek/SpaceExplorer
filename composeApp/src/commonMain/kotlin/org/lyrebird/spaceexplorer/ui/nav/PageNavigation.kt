package org.lyrebird.spaceexplorer.ui.nav

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.lyrebird.spaceexplorer.domain.model.uiModel.LaunchDetail
import org.lyrebird.spaceexplorer.ui.screen.home.HomeScreen
import org.lyrebird.spaceexplorer.ui.screen.launchDetail.LaunchDetailScreen


@Composable
fun PageNavigation(selectedPage: String) {
    val navController = rememberNavController()


    Scaffold { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = selectedPage
        ) {

            composable("home") {
                HomeScreen(navController = navController)
            }
            composable<LaunchDetail> { backStackEntry ->
                val args = backStackEntry.toRoute<LaunchDetail>()

                LaunchDetailScreen(
                    navController = navController,
                    launchID = args.launchID,
                    rocketID = args.rocketID
                )
            }

        }
    }


}