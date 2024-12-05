package com.example.myapplication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.composable.ClickableBoxData
import com.example.myapplication.ui.composable.CustomRow
import com.example.myapplication.ui.composable.TitleForEachPage
import com.example.myapplication.ui.navigation.HomeDestination
import dev.enro.annotations.NavigationDestination


@Composable
@NavigationDestination(HomeDestination::class)
fun HomeScreen(viewModel: HomeScreenViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    val onDetailClicked = viewModel::onDetailClicked
    val onEditClicked = viewModel::onEditClicked
    HomeScreen(state, onDetailClicked, onEditClicked)
}

@Composable
private fun HomeScreen(
    state: List<Goal>,
    onDetailClicked: (id: Int) -> Unit,
    onEditClicked: (id: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleForEachPage("Saving Goal!", "Saving for your future :)")
        var boxesData = state.map { goal: Goal ->
            ClickableBoxData(
                goal,
                onClick = {
                    onDetailClicked(
                        goal.id
                    )
                },
                isEdit = true,
            )
        }
        boxesData += ClickableBoxData(
            goal = Goal(
                id = 3,
                name = "Add",
                targetAmount = 10,
                currentAmount = 1
            ),
            isEdit = false,
            onClick = {
                onEditClicked(0)
            },
            )
        boxesData.chunked(2).forEach { pair ->
            CustomRow(pair)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(
        listOf(
            Goal(
                id = 1,
                name = "Testd as fa sda adsf asff",
                targetAmount = 100,
                currentAmount = 20
            ),
            Goal(
                id = 2,
                name = "Test1",
                targetAmount = 330,
                currentAmount = 300
            ),
        ),
        {},
        {}
    )
}
