package com.example.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.FlowOfGoals
import com.example.myapplication.domain.FlowOfGoals.Companion.invoke
import com.example.myapplication.domain.models.Goal
import com.example.myapplication.ui.navigation.DetailDestination
import com.example.myapplication.ui.navigation.EditDestination
import com.example.myapplication.ui.navigation.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.enro.core.push
import dev.enro.viewmodel.navigationHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    flowOfGoals: FlowOfGoals,
) : ViewModel() {
    private val navigationHandle by navigationHandle<HomeDestination>()

    val state =  MutableStateFlow(emptyList<Goal>())

    init {
        flowOfGoals().mapLatest {goals->
            Log.d("goals: ", "state = $goals")
            state.value = goals
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


        //todo: update the edited data to the database
    }


}