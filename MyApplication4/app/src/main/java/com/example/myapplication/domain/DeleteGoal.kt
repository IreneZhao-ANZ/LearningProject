package com.example.myapplication.domain

import com.example.myapplication.domain.models.Goal

fun interface DeleteGoal {
    suspend fun deleteGoal(goal: Goal)

    companion object {
        suspend operator fun DeleteGoal.invoke(goal:Goal) = deleteGoal(goal = goal)
    }
}
