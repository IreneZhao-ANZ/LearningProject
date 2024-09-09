package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.navigation.DetailDestination
import com.example.myapplication.navigation.HomeDestination
import dev.enro.annotations.NavigationDestination
import dev.enro.core.compose.navigationHandle
import dev.enro.core.push


@Composable
@NavigationDestination(HomeDestination::class)
fun HomeScreen() {
    val navigation = navigationHandle<HomeDestination>()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleForEachPage("Saving Goal!", "Saving for your future :)")

        val boxesData = listOf(
            ClickableBoxData(targetAmount = 100, currentAmount = 20, isEdit = true, text = "Test1"),
            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test2"),
            ClickableBoxData(targetAmount = 100, currentAmount = 40, isEdit = true, text = "Test3"),
            ClickableBoxData(targetAmount = 100, currentAmount = 50, isEdit = true, text = "Test4"),
            ClickableBoxData(
                targetAmount = 100,
                currentAmount = 100,
                isEdit = true,
                text = "Test5"
            ),
            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test6"),
            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test7"),
            ClickableBoxData(targetAmount = 100, currentAmount = 30, isEdit = true, text = "Test9", onClick = {
                //navigation.push(DetailDestination())
            }),
            ClickableBoxData(isEdit = false, text = "Add",
                onClick = {
                    navigation.push(DetailDestination())
//                navigation.push(DetailDestination(
//                    goalName = "PS5",
//                    targetAmount = 500,
//                    currentAmount = 100
//                ))
           }
            )
        )
        boxesData.chunked(2).forEach { pair ->
            CustomRow(pair)
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    HomeScreen()
//}
