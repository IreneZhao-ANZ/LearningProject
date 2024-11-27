package com.example.myapplication.domain

import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.flow.Flow

fun interface FlowOfGoal {
    fun flowOfGoal(id:Int): Flow<Goal>

    companion object {
        operator fun FlowOfGoal.invoke(id:Int) = flowOfGoal(id = id)
    }

}

