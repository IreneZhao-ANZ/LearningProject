package com.example.myapplication.ui.home

import android.util.Log
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
import com.example.myapplication.ClickableBoxData
import com.example.myapplication.CustomRow
import com.example.myapplication.TitleForEachPage
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.HomeDestination
import dev.enro.annotations.NavigationDestination


@Composable
@NavigationDestination(HomeDestination::class)
fun HomeScreen(viewModel: HomeScreenViewModel = viewModel()) {
    println("Start HomeScreen-----------------1---------------")
    val state by viewModel.state.collectAsState()
    Log.d("IreneLog-HomeScreen: ", "state = $state")
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

        println("Start HomeScreen-----------------2---------------")
        var boxesData = state.map { goal: Goal ->
            ClickableBoxData(
                goal,
                onClick = {
                    Log.d("IreneLog goal id: ", "state = ${goal.id}")
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

//        val boxesData = listOf(
//            ClickableBoxData(
//                //targetAmount = 100, currentAmount = 20, isEdit = true, text = "Test1",
//                Goal,
//                onClick = {
//                viewModel.onDetailClicked(
//
//                    //isEdit = true,
//                    "Test1",
//                    100,
//                    20
//                )
//            }),
//            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test2", onClick = {
//                viewModel.onDetailClicked(
//                    //isEdit = true,
//                    "Test2",
//                    100,
//                    30
//                )
//            }),
//            ClickableBoxData(targetAmount = 100, currentAmount = 40, isEdit = true, text = "Test3", onClick = {
//                viewModel.onDetailClicked(
//                    //isEdit = true,
//                    "Test3",
//                    100,
//                    40
//                )
//            }),
//            ClickableBoxData(targetAmount = 100, currentAmount = 50, isEdit = true, text = "Test4", onClick = {
//                viewModel.onDetailClicked(
//                //isEdit = true,
//                "Test4",
//                100,
//                50
//            )
//            }),
//
//
//            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test5", onClick = {
//                viewModel.onDetailClicked(
//                    //isEdit = true,
//                    "Test5",
//                    100,
//                    30
//                )
//            }),
//        listOf(ClickableBoxData(
//            isEdit = false,
//            goal = Goal(
//                id = 0,
//                name = "New",
//                targetAmount = 0,
//                currentAmount = 0
//            ),
//            onClick = {
//                onDetailClicked(
//                    0
//                )
//            }
//        ))
//
//        )
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
