package com.example.myapplication.domain.mappers

import com.example.myapplication.data.room.GoalEntity
import com.example.myapplication.data.room.SortType
import com.example.myapplication.domain.models.Goal


fun Goal.toDb() = GoalEntity(
    id = id,
    name = name,
    targetAmount = targetAmount,
    currentAmount = currentAmount,
    sortType = SortType.ID
)
