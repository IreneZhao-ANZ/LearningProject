package com.example.myapplication.ui.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.composable.ProgressCircle
import com.example.myapplication.ui.composable.TitleForEachPage
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.theme.Anzx100
import dev.enro.annotations.NavigationDestination

@Composable
@NavigationDestination(DetailDestination::class)
fun DetailScreen(
    viewModel: DetailScreenViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    val onEditClicked = viewModel::onEditClicked
    val onDeleteClicked = viewModel::onDeleteClicked
    DetailScreen(state, onEditClicked, onDeleteClicked)
}

@Composable
private fun DetailScreen(
    goal: Goal,
    onEditClicked: (id: Int) -> Unit,
    onDeleteClicked: (goal: Goal) -> Unit
) {
    if (goal.id == 0) {
        // Handle the default state (e.g., show a placeholder or empty state)
        Text("No goal selected")
    } else {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleForEachPage("Saving Goal!", "Saving for your future :)")
            ProgressCircle(
                modifier = Modifier
                    .padding(top = 30.dp),
                targetAmount = goal.targetAmount,
                currentAmount = goal.currentAmount,
                radius = 70.dp,
                strokeWidth = 90f,
                fontSize = 50.sp
            )

            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = goal.name,
                fontSize = 30.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
            )

            Text(
                modifier = Modifier.padding(top = 30.dp),
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                text = "Target Amount: ${goal.targetAmount}",
                fontSize = 15.sp
            )

            Text(
                text = "Current Amount: ${goal.currentAmount}",
                modifier = Modifier
                    .padding(top = 20.dp),
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            CustomButton(text = "Edit",
                modifier = Modifier.padding(top = 80.dp),
                onClick = { onEditClicked(goal.id) }
            )

            CustomButton(text = "Delete",
                modifier = Modifier
                    .padding(top = 20.dp),
                containerColor = Color.White,
                textColor = Anzx100,
                borderColor = Anzx100,
                onClick = { onDeleteClicked(goal) })
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Anzx100,
    textColor: Color = Color.White,
    width: Dp = 280.dp,
    height: Dp = 50.dp,
    borderColor: Color = Anzx100,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 24.dp,
    fontSize: TextUnit = 20.sp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier

            .size(width = width, height = height)
            .border(
                borderWidth,
                borderColor,
                shape = RoundedCornerShape(cornerRadius)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onClick
    ) {
        Text(
            color = textColor,
            text = text,
            fontSize = fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        goal = Goal(id = 1, name = "Sample Goal", targetAmount = 1000, currentAmount = 500),
        onEditClicked = {},
        onDeleteClicked = {}
    )
}




