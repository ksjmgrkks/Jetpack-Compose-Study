package com.kks.readerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kks.readerapp.screens.SplashScreen
import com.kks.readerapp.screens.home.HomeScreen
import com.kks.readerapp.screens.home.HomeScreenViewModel
import com.kks.readerapp.screens.login.LoginScreen
import com.kks.readerapp.screens.search.BookSearchScreen
import com.kks.readerapp.screens.search.BooksSearchViewModel
import com.kks.readerapp.screens.stats.ReaderStatsScreen

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
    }
}