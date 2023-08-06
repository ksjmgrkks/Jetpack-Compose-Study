package com.bawp.jettip_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bawp.jettip_test.components.InputField
import com.bawp.jettip_test.ui.theme.JetTipAppTheme
import com.bawp.jettip_test.util.calculateTotalPerPerson
import com.bawp.jettip_test.widgets.RoundIconButton
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetTipAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(150.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(text = "풋살 인당 지불 금액", style = MaterialTheme.typography.h5)
            Text(text = "${total}원", style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
        }

    }

}
@ExperimentalComposeUiApi
@Preview
@Composable
fun MainContent(){
    val totalPerPersonState = remember {
        mutableStateOf(0.0)
    }
    val personCountState = remember {
        mutableStateOf(1)
    }
    val totalBillState = remember {
        mutableStateOf("")
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val range = IntRange(start = 1, endInclusive = 100)
    BillForm(
        totalPerPersonState = totalPerPersonState,
        personCountState = personCountState,
        range = range,
        totalBillState = totalBillState,
        sliderPositionState = sliderPositionState
    ){

    }
}
@ExperimentalComposeUiApi
@Composable
fun BillForm(modifier: Modifier = Modifier,
             range: IntRange = 1..100,
             totalBillState: MutableState<String>,
             personCountState: MutableState<Int>,
             totalPerPersonState: MutableState<Double>,
             sliderPositionState: MutableState<Float>,
             onValChange: (String) -> Unit = {}
            ){
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val beverageCount = ((sliderPositionState.value) * 10).roundToInt()

    TopHeader(totalPerPerson = totalPerPersonState.value)

    Surface(
        modifier = modifier
            .padding(15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(modifier = modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {
            InputField(
                valueState = totalBillState,
                labelId = "금액 입력",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                },
                onValueChange = {
                    if(it.isEmpty()){
                        totalBillState.value = "0"
                    }else{
                        totalBillState.value = it
                    }

                    totalPerPersonState.value = calculateTotalPerPerson(
                        enterBill = totalBillState.value.toDouble(),
                        beverageCount = beverageCount,
                        personCount = personCountState.value
                    )
                }

            )
            if(validState) {
                Row(modifier = modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.Start){
                    Text("인원 수",
                        modifier = Modifier.align(
                            alignment = Alignment.CenterVertically
                        ))
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(modifier = Modifier.padding(horizontal = 3.dp),
                    horizontalArrangement = Arrangement.End) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = {
                                personCountState.value =
                                    if(personCountState.value > 1) personCountState.value - 1 else 1

                                totalPerPersonState.value = calculateTotalPerPerson(
                                    enterBill = totalBillState.value.toDouble(),
                                    beverageCount = beverageCount,
                                    personCount = personCountState.value
                                )
                            })
                        Text(text = "${personCountState.value}",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp))

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            onClick = {
                                if(personCountState.value < range.last)
                                    personCountState.value += 1

                                totalPerPersonState.value = calculateTotalPerPerson(
                                    enterBill = totalBillState.value.toDouble(),
                                    beverageCount = beverageCount,
                                    personCount = personCountState.value
                                )
                            })

                    }
                }

                Row ( modifier = modifier.
                        padding(horizontal = 3.dp,
                        vertical = 12.dp)
                        ){
                    Text(text = "음료수 값", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(200.dp))
                    Text(text = "${beverageCount * 3000}원", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                }
                
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "음료수 ${beverageCount}개 (개당 3000원)")
                    
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    //Slider
                    Slider(
                        value = sliderPositionState.value,
                        onValueChange = {
                            sliderPositionState.value = it
                            totalPerPersonState.value = calculateTotalPerPerson(
                                enterBill = totalBillState.value.toDouble(),
                                beverageCount = beverageCount,
                                personCount = personCountState.value
                            )
                        },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    steps = 10,
                    onValueChangeFinished = {

                    })
                }
            }else{
                Box{ }
            }
        }
    }
}