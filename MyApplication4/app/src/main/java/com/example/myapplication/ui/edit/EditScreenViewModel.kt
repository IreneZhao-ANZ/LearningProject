package com.example.myapplication.ui.edit

import android.util.Log
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

    private val navigationHandle by navigationHandle<EditDestination>()
    init {
        viewModelScope.launch {
            flowOfGoal(navigationHandle.key.id).collectLatest { goal ->
                Log.d("IreneLog is: ", "Edie = $goal")
                state.update {currentState ->
                    Log.d("IreneLog currentState is: ", "currentState = ${currentState}")
                    currentState.copy(
                        id = goal.id,
                        name = goal.name,
                        targetAmount = goal.targetAmount,
                        currentAmount = goal.currentAmount
                    )
                }
                Log.d("IreneLog currentState is: ", "Edit - currentState = ${state}")
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
        viewModelScope.launch{
            upsertGoal(state.value)
            println("IreneLog-EditScreenViewModel-onDoneClicked: state.value=${state.value}")
            navigationHandle.push(
                OperationDoneDestination()
            )
        }


    }



}


