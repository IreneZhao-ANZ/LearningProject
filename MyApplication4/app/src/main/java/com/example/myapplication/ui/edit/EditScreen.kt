package com.example.myapplication.ui.edit

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.TitleForEachPage
import com.example.myapplication.ui.detail.CustomButton
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.theme.TextColor
import dev.enro.annotations.NavigationDestination

@Composable
@NavigationDestination(EditDestination::class)
fun EditScreen(viewModel: EditScreenViewModel = viewModel()) {
    val state = viewModel.state.value
    var goalName by remember { mutableStateOf(state.name) }
    var targetAmount by remember { mutableStateOf(state.targetAmount.toString()) }
    var currentAmount by remember { mutableStateOf(state.currentAmount.toString()) }
    //EditScreen(state, viewModel::onDoneClicked)
    EditScreen(
        goalName = goalName,
        targetAmount = targetAmount,
        currentAmount = currentAmount,
        onGoalNameChange = { goalName = it },
        onTargetAmountChange = { targetAmount = it },
        onCurrentAmountChange = { currentAmount = it },
        onSaveClick = {
            viewModel.updateGoalName(goalName)
            viewModel.updateTargetAmount(targetAmount.toIntOrNull() ?: 0)
            viewModel.updateCurrentAmount(currentAmount.toIntOrNull() ?: 0)
            viewModel.onDoneClicked()
        }
    )

}

@Composable
private fun EditScreen(
    goalName: String,
    targetAmount: String,
    currentAmount: String,
    onGoalNameChange: (String) -> Unit,
    onTargetAmountChange: (String) -> Unit,
    onCurrentAmountChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        TitleForEachPage("Saving Goal!", "Saving for your future :)")
        DataInput(
            inputName = "goalName",
            inputInitial = "Saving Goal",
            onValueChange = onGoalNameChange
        )
        DataInput(
            inputName = "Target Amount",
            inputInitial = "$$targetAmount",
            modifier = Modifier.padding(top = 20.dp),
            isDigit = true,
            onValueChange = onTargetAmountChange
        )
        DataInput(
            inputName = "Current Amount",
            inputInitial = "$$currentAmount",
            modifier = Modifier.padding(top = 20.dp),
            isDigit = true,
            onValueChange = onCurrentAmountChange
        )
        CustomButton(text = "Save", modifier = Modifier.padding(top = 130.dp), onClick =
        onSaveClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataInput(
    inputName: String,
    inputInitial: String,
    modifier: Modifier = Modifier,
    isDigit: Boolean = false,
    onValueChange: (String) -> Unit,

    ) {
    var input by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(top = 20.dp)) {
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            fontSize = 20.sp,
            text = inputName,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Normal,
            color = TextColor
        )
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
                if (isDigit == true) {
                    if (it.all { char -> char.isDigit() }) {
                        input = it
                        onValueChange(it)
                    }
                } else {
                    input = it
                    onValueChange(it)
                }
            },
            placeholder = {
                Text(
                    text = inputInitial,
                    color = Color.LightGray
                )
            },
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

@Preview(showBackground = true)
@Composable
fun DataInputPreview() {

    EditScreen(

        goalName = "Test",
            targetAmount = "100",
            currentAmount = "20",

        {},
        { },
        { },
        { }
    )

}
