package com.android.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.movieapp.navigation.MovieNavigation
import com.android.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp {
                MovieNavigation()
            }
        }
    }
}

//Container Function
@Composable
fun MovieApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieApp {
        MovieNavigation()
    }
}