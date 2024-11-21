package com.example.myapplication.ui.operationDone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.TitleForEachPage
import com.example.myapplication.ui.detail.CustomButton
import com.example.myapplication.ui.navigation.OperationDoneDestination
import com.example.myapplication.ui.theme.Brand100
import dev.enro.annotations.NavigationDestination


@Composable
@NavigationDestination(OperationDoneDestination::class)
fun OperationDoneScreen(viewModel: OperationDoneScreenViewModel = viewModel<OperationDoneScreenViewModel>()) {
    val DoneButtonClicked = viewModel::onDoneClicked
    OperationDoneScreen(DoneButtonClicked)
}

@Composable
private fun OperationDoneScreen(doneButtonClicked: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleForEachPage("Saving Goal!", "Saving for your future :)")
        Text(
            text = "Done~!",
            modifier = Modifier.padding(top = 100.dp),
            fontSize = 50.sp,
            color = Brand100,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        )
        CustomButton(text = "Back to Home",
            modifier = Modifier.padding(top = 400.dp),
            onClick = doneButtonClicked
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OperationDonePreview() {
    OperationDoneScreen({})
}
