package com.example.myapplication.domain

import com.example.myapplication.domain.models.Goal

fun interface UpsertGoal {
    suspend fun upsertGoal(goal: Goal)

    companion object {
        suspend operator fun UpsertGoal.invoke(goal: Goal) = upsertGoal(goal = goal)
    }
}
