package com.example.projectmanager.navigation

sealed class Screen(val route: String) {
    object ProjectsList : Screen("projects_list")
    object AddProject : Screen("add_project")
    object ProjectDetails : Screen("details/{projectId}") {
        fun createRoute(projectId: Int) = "details/$projectId"
    }
}