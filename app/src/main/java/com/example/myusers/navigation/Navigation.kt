package com.example.myusers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myusers.screens.UserDetailsScreen
import com.example.myusers.screens.UsersScreen
import com.example.myusers.viemodels.UserViewModel

@Composable
fun Navigation(navHostController: NavHostController, viewModel: UserViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Users.route
    ) {
        composable(Screens.Users.route) {
            UsersScreen(
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
        composable(
            route = Screens.UserDetails.route,
            arguments = listOf(
                navArgument(ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val argument = it.arguments?.getString(ARGUMENT_KEY)
            UserDetailsScreen(viewModel = viewModel, email = argument)
        }
    }
}


