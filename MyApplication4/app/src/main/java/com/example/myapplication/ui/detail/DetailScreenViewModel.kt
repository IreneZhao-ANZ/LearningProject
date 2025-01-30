package com.example.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.DeleteGoal
import com.example.myapplication.domain.DeleteGoal.Companion.invoke
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.FlowOfGoal.Companion.invoke
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.navigation.OperationDoneDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    flowOfGoal: FlowOfGoal,
    private val deleteGoal: DeleteGoal
) : ViewModel() {

    private val navigationHandle by navigationHandle<DetailDestination>()
    val state: MutableStateFlow<Goal> = MutableStateFlow(Goal(0, "", 1, 0))

    init {
        viewModelScope.launch {
            flowOfGoal(navigationHandle.key.id).collectLatest { goal ->
                state.update { currentState ->
                    currentState.copy(
                        id = goal.id,
                        name = goal.name,
                        targetAmount = goal.targetAmount,
                        currentAmount = goal.currentAmount
                    )
                }
            }
        }
    }

    fun onEditClicked(
        id: Int
    ) {
        navigationHandle.push(
            EditDestination(id)
        )
    }


    fun onDeleteClicked(goal: Goal) {
        viewModelScope.launch {
            println("Deleting goal: $goal")
            deleteGoal(goal)
            //state.value = Goal(0, "", 1, 0)

            navigationHandle.push(
                OperationDoneDestination()
            )
        }
    }


}
