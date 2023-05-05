package com.kks.readerapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.kks.readerapp.navigation.ReaderScreens
import com.kks.readerapp.ui.theme.LogoGreen
import com.kks.readerapp.ui.theme.LogoLightGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController = NavController(context = LocalContext.current)) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 900,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(1000L)

        navController.navigate(ReaderScreens.LoginScreen.name)
    }

    Surface(modifier = Modifier
        .padding(15.dp)
        .size(380.dp)
        .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 5.dp,
            color = LogoLightGreen)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "책을 읽어요",
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.h3,
                color = LogoGreen)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "\"책 읽기와 글쓰기를 통해 성장하기 \"",
                style = MaterialTheme.typography.h6,
                color = LogoGreen.copy(alpha = 0.5f))
        }
    }
}