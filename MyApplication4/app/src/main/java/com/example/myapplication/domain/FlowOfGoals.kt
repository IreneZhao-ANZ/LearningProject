package com.example.myapplication.domain

import com.example.myapplication.domain.models.Goal
import kotlinx.coroutines.flow.Flow

fun interface FlowOfGoals {
    fun flowOfGoals(): Flow<List<Goal>>

    companion object {
        operator fun FlowOfGoals.invoke() = flowOfGoals()
    }
}

