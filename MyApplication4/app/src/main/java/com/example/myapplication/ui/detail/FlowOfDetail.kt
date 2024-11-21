package com.example.myapplication.ui.detail

import com.example.myapplication.domain.models.Goal

fun interface FlowOfDetailGoal {
    fun flowOfDetailGoal(filter: Goal): List<Goal>

    companion object {
        operator fun FlowOfDetailGoal.invoke(filter: Goal) = flowOfDetailGoal(filter = filter)
    }
}
