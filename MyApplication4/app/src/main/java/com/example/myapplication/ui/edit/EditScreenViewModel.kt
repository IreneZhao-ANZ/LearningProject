package com.example.myapplication.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.FlowOfGoal
import com.example.myapplication.domain.FlowOfGoal.Companion.invoke
import com.example.myapplication.domain.UpsertGoal
import com.example.myapplication.domain.UpsertGoal.Companion.invoke
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.navigation.OperationDoneDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    flowOfGoal: FlowOfGoal,
    private val upsertGoal: UpsertGoal
) : ViewModel() {
    val state: MutableStateFlow<Goal> = MutableStateFlow(Goal(0, "", 1, 0))
    val snackbarMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    private val navigationHandle by navigationHandle<EditDestination>()

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

    fun updateGoalName(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun updateTargetAmount(amount: Int) {
        state.value = state.value.copy(targetAmount = amount)
    }

    fun updateCurrentAmount(amount: Int) {
        state.value = state.value.copy(currentAmount = amount)
    }

    fun onDoneClicked() {
        if (state.value.targetAmount >= state.value.currentAmount && state.value.targetAmount > 0) {
            viewModelScope.launch {
                if (state.value.targetAmount >= state.value.currentAmount && state.value.targetAmount > 0) {
                    upsertGoal(state.value)
                    navigationHandle.push(
                        OperationDoneDestination()
                    )

                }
            }
        }else{
                snackbarMessage.value =
                    "Invalid value: target amount must be greater than or equal to current amount and greater than 0"
            }


    }
}


