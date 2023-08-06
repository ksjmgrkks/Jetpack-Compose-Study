package com.android.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.movieapp.data.Movie
import com.android.movieapp.data.getMovies
import com.android.movieapp.navigation.MovieScreens
import com.android.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 2.dp
        ) {
            Text(text = "영화 목록")
        }
    }) {
        MainContent(
            modifier = Modifier.padding(it),
            navController = navController)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
                }
            }
        }
    }
}

