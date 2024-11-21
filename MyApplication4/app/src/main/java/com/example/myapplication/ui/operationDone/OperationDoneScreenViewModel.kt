package com.example.myapplication.ui.operationDone

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.ui.navigation.HomeDestination
import com.example.myapplication.ui.navigation.OperationDoneDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import javax.inject.Inject

@HiltViewModel
class OperationDoneScreenViewModel @Inject constructor(
    flowOfGoals: FlowOfGoals,
) : ViewModel() {
    private val navigationHandle by navigationHandle<OperationDoneDestination>()

    fun onDoneClicked(
    ) {
        navigationHandle.push(
            HomeDestination
        )

    }

}
