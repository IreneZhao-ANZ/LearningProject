package com.example.myapplication.ui.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    flowOfGoal: FlowOfGoal,
    private val upsertGoal: UpsertGoal
) : ViewModel() {
    var state: MutableState<Goal> = mutableStateOf(Goal(0, "", 0, 0))

    private val navigationHandle by navigationHandle<EditDestination>()
    init {
        flowOfGoal(navigationHandle.key.id).mapLatest {
            if (it != null) {
                state.value = it
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


