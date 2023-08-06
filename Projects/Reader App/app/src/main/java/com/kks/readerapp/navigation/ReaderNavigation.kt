package com.kks.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kks.readerapp.screens.SplashScreen
import com.kks.readerapp.screens.details.BookDetailsScreen
import com.kks.readerapp.screens.home.HomeScreen
import com.kks.readerapp.screens.home.HomeScreenViewModel
import com.kks.readerapp.screens.login.LoginScreen
import com.kks.readerapp.screens.search.BookSearchScreen
import com.kks.readerapp.screens.search.BooksSearchViewModel
import com.kks.readerapp.screens.stats.ReaderStatsScreen
import com.kks.readerapp.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderStatsScreen(navController = navController, viewModel = homeViewModel)
        }

        composable(ReaderScreens.HomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }

        composable(ReaderScreens.BookSearchScreen.name) {
            val searchViewModel = hiltViewModel<BooksSearchViewModel>()
            BookSearchScreen(navController = navController, viewModel = searchViewModel)
        }

        val detailName = ReaderScreens.BookDetailsScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailsScreen(navController = navController, bookId = it.toString())
            }
        }

        val updateName = ReaderScreens.BookUpdateScreen.name
        composable("$updateName/{bookItemId}",
            arguments = listOf(navArgument("bookItemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("bookItemId").let {
                BookUpdateScreen(navController = navController, bookItemId = it.toString())
            }

        }

    }
}