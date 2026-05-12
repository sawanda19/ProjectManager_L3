package com.example.projectmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.projectmanager.ui.screens.AddProjectScreen
import com.example.projectmanager.ui.screens.DetailsProjectScreen
import com.example.projectmanager.ui.screens.ProjectsListScreen
import com.example.projectmanager.viewmodel.ProjectViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    viewModel: ProjectViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProjectsList.route
    ) {
        composable(Screen.ProjectsList.route) {
            ProjectsListScreen(
                viewModel = viewModel,
                onAddProject = { navController.navigate(Screen.AddProject.route) },
                onProjectClick = { projectId ->
                    navController.navigate(Screen.ProjectDetails.createRoute(projectId))
                }
            )
        }

        composable(Screen.AddProject.route) {
            AddProjectScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.ProjectDetails.route,
            arguments = listOf(navArgument("projectId") { type = NavType.IntType })
        ) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getInt("projectId") ?: return@composable
            DetailsProjectScreen(
                projectId = projectId,
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onProjectDeleted = {
                    navController.popBackStack(Screen.ProjectsList.route, inclusive = false)
                }
            )
        }
    }
}