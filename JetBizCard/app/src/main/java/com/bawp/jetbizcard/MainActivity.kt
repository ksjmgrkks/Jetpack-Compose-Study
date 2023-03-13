package com.bawp.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bawp.jetbizcard.ui.theme.JetBizCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                CreateInfo()
                Row(modifier = Modifier.padding(10.dp)) {
                    NotionPageButton(modifier = Modifier.padding(horizontal = 20.dp))
                    PortfolioButton(
                        buttonClickedState = buttonClickedState,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box { }
                }
            }
        }
    }
}

@Composable
fun NotionPageButton(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Button(
        modifier = modifier,
        onClick = {
            uriHandler.openUri("https://vigorous-cartoon-8db.notion.site/Kyuseong-Kim-1abc73fc74d648da85914c7676e8e358")
        }
    ) {
        Text(text = "Notion Page")
    }
}

@Composable
fun PortfolioButton(buttonClickedState: MutableState<Boolean>, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier,
        onClick = {
            buttonClickedState.value = !buttonClickedState.value
        }
    ) {
        Text(text = "Portfolio")
    }
}

@Preview
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(listOf("어디야?", "어바웃 미"))
        }

    }

}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) {
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(7.dp)
                ) {
                    CreateImageProfile(modifier = Modifier.size(100.dp))
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = "프로젝트 명", style = MaterialTheme.typography.body1)
                        Text(text = it, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = "Kyu Seong Kim",
        color = MaterialTheme.colors.primaryVariant,
        style = MaterialTheme.typography.h3
    )
    Divider(
        modifier = Modifier.padding(15.dp),
        thickness = 2.dp
    )
    Text(text = "ksjmgrkks", color = Color.LightGray, style = MaterialTheme.typography.subtitle2)
    Text(text = "Android developer", style = MaterialTheme.typography.subtitle2)
    Text(
        modifier = Modifier.padding(horizontal = 40.dp, vertical = 30.dp),
        style = MaterialTheme.typography.subtitle2,
        text = "Hi there \uD83D\uDC4B\n" +
                "I'm an Android developer interested in 'Growth Together'." +
                "I'm constantly learning and uploading what I learn to my Blog and GitHub." +
                "Recently, I've been studying Jetpack Compose and clean app architecture.\n\n" +
                "If you want to know more about me, please visit my notion page below",
    )
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {

        Image(
            painter = painterResource(id = R.drawable.about_me_image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}