package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.domain.FlowOfGoals.Companion.invoke
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.navigation.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    flowOfGoals: FlowOfGoals,
) : ViewModel() {
    private val navigationHandle by navigationHandle<HomeDestination>()

    val state: MutableStateFlow<List<Goal>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            flowOfGoals().collectLatest { goals ->
                state.update {
                    goals
                }
            }
        }
    }

    fun onDetailClicked(
        id: Int
    ) {
        navigationHandle.push(
            DetailDestination(
                id
            )
        )
    }

    fun onEditClicked(
        id: Int
    ) {
        navigationHandle.push(
            EditDestination(id)
        )
    }
}
