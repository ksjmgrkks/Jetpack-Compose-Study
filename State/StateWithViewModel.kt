import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    // MutableStateFlow로 선언
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count

    fun incrementCount() {
        viewModelScope.launch {
            _count.value++
        }
    }
}

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen(myViewModel: MyViewModel = viewModel()) {
    // collectAsState를 사용하여 StateFlow를 관찰
    val count by myViewModel.count.collectAsState()

    Button(onClick = { myViewModel.incrementCount() }) {
        Text("Clicked $count times")
    }
}
