import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material.Button
import androidx.compose.material.Text

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) } /* 컴포넌트가 제거되거나 화면이 회전되면 count값 소멸 */

    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}

@Composable
fun CounterSaveable() {
    var count by rememberSaveable { mutableStateOf(0) } /* 컴포넌트가 제거되거나 화면이 회전되어도 count값 유지 */

    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MyScreen() {
    var name by remember { mutableStateOf("John") }
    Greeting(name = name)
    Button(onClick = { name = "Jane" } /* recomposition 발생! */ ) {
        Text("Change name")
    }
}
