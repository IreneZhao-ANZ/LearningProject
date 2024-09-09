package com.example.myapplication

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.navigation.EditDestination
import com.example.myapplication.navigation.OperationDoneDestination
import com.example.myapplication.ui.theme.TextColor
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationHandle
import dev.enro.core.compose.navigationHandle
import dev.enro.core.push

@Composable
@NavigationDestination(EditDestination::class)
fun EditScreen() {
    val navigation : NavigationHandle = navigationHandle<EditDestination>()
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        TitleForEachPage("Saving Goal!", "Saving for your future :)")
        DataInput(inputName = "Name", inputInitial = "Saving Goal")
        DataInput(inputName = "Target Amount", inputInitial = "$", modifier = Modifier.padding(top = 20.dp), isDigit = true)
        DataInput(inputName = "Current Amount", inputInitial = "$", modifier = Modifier.padding(top = 20.dp), isDigit = true)
        CustomButton(text = "Save", modifier = Modifier.padding(top = 130.dp), onClick = ({
            navigation.push(OperationDoneDestination())
        }))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataInput(inputName: String, inputInitial:String,modifier: Modifier = Modifier, isDigit:Boolean = false) {
    var input by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(top = 20.dp)) {
        Text(modifier = Modifier
            .padding(bottom = 10.dp),
            fontSize = 20.sp,
            text = "$inputName",
            fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
            color = TextColor)
        TextField(
            modifier = Modifier
                .padding(top = 5.dp)
                .border(
                    1.dp,
                    androidx.compose.ui.graphics.Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .size(width = 320.dp, height = 60.dp),
            value = input,
            onValueChange = {
                if(isDigit == true){
                    if (it.all { char -> char.isDigit() }) {
                        input = it
                    }
                }else{
                    input = it
                }
            },
            placeholder = {
                Text(text = "$inputInitial",
                color = Color.LightGray
            ) },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = if (isDigit) {
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                )
            } else {
                KeyboardOptions.Default
            }
            )

    }

}

//@Preview(showBackground = true)
//@Composable
//fun DataInputPreview() {
//
//    EditScreen()
//
//}
