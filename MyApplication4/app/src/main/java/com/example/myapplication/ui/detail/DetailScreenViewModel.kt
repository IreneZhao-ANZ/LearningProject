package com.example.myapplication.ui.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.navigation.EditDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    flowOfGoal: FlowOfGoal
) : ViewModel() {

    private val navigationHandle by navigationHandle<DetailDestination>()
    val state: MutableState<Goal> = mutableStateOf(Goal(0, "", 0, 0))

    //val resultChannel by registerForNavigationResult<GoalData> { result -> goalName;targetAmount;currentAmount }
//    init{
//        println("goalName: $goalName, targetAmount: $targetAmount, currentAmount: $currentAmount")
//    }
    init {
        // state.value = flowOfGoal(navigationHandle.key.id)
    }

    fun onEditClicked(
        id: Int
    ) {
        navigationHandle.push(
            EditDestination(id)
        )


        //todo: update the edited data to the database
    }
}
