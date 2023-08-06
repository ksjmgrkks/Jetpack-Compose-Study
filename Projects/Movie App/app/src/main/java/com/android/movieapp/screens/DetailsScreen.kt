package com.android.movieapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.android.movieapp.data.Movie
import com.android.movieapp.data.getMovies
import com.android.movieapp.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 2.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "뒤로가기 버튼",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
            }
            Spacer(modifier = Modifier.width(100.dp))
            Text(text = "상세 정보")
        }
    }) {
        Surface(
            modifier = Modifier
                 //출처: https://stackoverflow.com/questions/72084865/content-padding-parameter-it-is-not-used
                .padding(it)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "포토", style = MaterialTheme.typography.h5)
                HorizontalScrollableImageView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList.first().images) { image ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 5.dp) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model =  image,
                        imageLoader = ImageLoader.
                        Builder(LocalContext.current).
                        crossfade(true).
                        build()),
                    contentScale = ContentScale.Crop,
                    contentDescription = "영화 포스터")
            }
        }
    }
}