package com.kks.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kks.weatherforecastapp.ui.theme.JetWeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()

        }
    }
}

@Composable
fun WeatherApp() {
    JetWeatherForecastTheme {
        Surface(color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()) {
               Column(verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "빌드 성공!")
               }
        }
    }
}