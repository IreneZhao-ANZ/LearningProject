package com.example.myapplication.domain.Mappers

import com.example.myapplication.data.room.GoalEntity
import com.example.myapplication.data.room.SortType
import com.example.myapplication.domain.models.Goal

fun List<Goal>.toDb() = map {
    it.toDb()
}

fun Goal.toDb() = GoalEntity(
    id = id,
    name = name,
    targetAmount = targetAmount,
    currentAmount = currentAmount,
    sortType = SortType.ID
)
