package com.kks.readerapp.navigation

import java.lang.IllegalArgumentException

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    BookSearchScreen,
    BookDetailsScreen,
    BookUpdateScreen,
    ReaderStatsScreen;
    companion object {
        fun fromRoute(route: String?): ReaderScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            HomeScreen.name -> HomeScreen
            BookSearchScreen.name -> BookSearchScreen
            BookDetailsScreen.name -> BookDetailsScreen
            BookUpdateScreen.name -> BookUpdateScreen
            ReaderStatsScreen.name -> ReaderStatsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}